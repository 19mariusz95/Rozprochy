package pl.edu.agh;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int PORT = 12345;
    private static final ExecutorService executor = Executors.newFixedThreadPool(100);
    private static final Map<String, ClientHandler> clientHandlerMap = new HashMap<>();

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
                    String[] split = message.split(":", 2);
                    String nick = split[0];
                    String mess = split[1];

                    synchronized (clientHandlerMap) {
                        clientHandlerMap.entrySet().stream().filter(a -> !a.getKey().equals(nick))
                                .forEach(a -> {
                                    try {
                                        int port = a.getValue().getSocket().getPort();
                                        String name = "localhost";
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
                while (!serverSocket.isClosed()) {
                    ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                    executor.submit(clientHandler);
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
        synchronized (clientHandlerMap) {
            clientHandlerMap.entrySet().stream()
                    .filter(a -> !a.getKey().equals(nick))
                    .forEach(a -> a.getValue().send(nick + ":" + message));
        }
    }
}
