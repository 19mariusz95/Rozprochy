package pl.edu.agh;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    public static final String MULTICAST_IP = "230.1.1.1";
    public static final int MULTICAST_PORT = 12344;
    public static final int PORT = 12345;
    private static Map<String, ClientHandler> clientHandlerMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        openTCP();
        openUDP();
    }

    private static void openUDP() {
        new Thread(() -> {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(PORT);
                while (!datagramSocket.isClosed()) {
                    byte[] bytes = new byte[500];
                    DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                    datagramSocket.receive(datagramPacket);
                    String message = new String(bytes);
                    System.out.println(message);
                    String[] split = message.split(":", 3);
                    String nick = split[0];
                    String type = split[1];
                    String mess = split[2];
                    if (type.equals("M") || type.equals("N")) {
                        clientHandlerMap.entrySet().stream().filter(a -> !a.getKey().equals(nick))
                                .forEach(a -> {
                                    try {
                                        int port = a.getValue().getSocket().getPort();
                                        String name;
                                        if (type.equals("M")) {
                                            name = "localhost";
                                        } else if (type.equals("N")) {
                                            name = MULTICAST_IP;
                                            port = MULTICAST_PORT;
                                        } else {
                                            return;
                                        }
                                        InetAddress IPAddress = InetAddress.getByName(name);
                                        byte[] newBytes = (nick + ":" + mess).getBytes();
                                        datagramSocket.send(new DatagramPacket(newBytes, newBytes.length, IPAddress, port));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private static void openTCP() {
        new Thread(() -> {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORT);
                while (true) {
                    ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                    clientHandler.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (serverSocket != null) {
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static boolean registerUser(String name, ClientHandler clientHandler) {
        clientHandlerMap.put(name, clientHandler);
        System.out.println("Registered " + name);
        return true;
    }

    public static void sendToAll(String nick, String message) {
        clientHandlerMap.entrySet().stream()
                .filter(a -> !a.getKey().equals(nick))
                .forEach(a -> a.getValue().send(nick + ":" + message));

    }
}
