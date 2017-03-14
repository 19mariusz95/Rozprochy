package pl.edu.agh;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {


    public static final String MULTICAST_IP = "230.1.1.1";
    public static final int MULTICAST_PORT = 12344;
    private static String nick;
    private static byte[] asciiBytes;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(Server.PORT));

        DatagramSocket datagramSocket = new DatagramSocket(socket.getLocalPort());

        openUDPListener(datagramSocket);
        openMultiCast();

        System.out.println("Enter nick:");
        Scanner scanner = new Scanner(System.in);
        nick = scanner.nextLine();

        asciiBytes = (nick + ":                    \n" +
                "                 _.-;;-._\n" +
                "          '-..-'|   ||   |\n" +
                "          '-..-'|_.-;;-._|\n" +
                "          '-..-'|   ||   |\n" +
                "    jgs   '-..-'|_.-''-._|").getBytes();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

        printWriter.write(nick + '\n');
        printWriter.flush();

        Scanner tcpScanner = new Scanner(socket.getInputStream());
        new Thread(() -> {
            while (tcpScanner.hasNextLine()) {
                System.out.println("TCP " + tcpScanner.nextLine());
            }

        }).start();

        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            if (message.equals("M")) {
                InetAddress IPAddress = InetAddress.getByName("localhost");
                datagramSocket.send(new DatagramPacket(asciiBytes, asciiBytes.length, IPAddress, Server.PORT));
            } else if (message.equals("N")) {
                InetAddress IPAddress = InetAddress.getByName(MULTICAST_IP);
                datagramSocket.send(new DatagramPacket(asciiBytes, asciiBytes.length, IPAddress, MULTICAST_PORT));
            } else {
                printWriter.write(message + '\n');
                printWriter.flush();
            }
        }

        socket.close();
    }

    private static void openUDPListener(DatagramSocket datagramSocket) {
        new Thread(() -> {
            try {
                while (!datagramSocket.isClosed()) {
                    byte[] buff = new byte[500];
                    DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
                    datagramSocket.receive(datagramPacket);
                    String message = new String(buff);
                    System.out.println("UDP " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void openMultiCast() {
        new Thread(() -> {
            try {
                MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT);
                multicastSocket.joinGroup(InetAddress.getByName(MULTICAST_IP));
                while (!multicastSocket.isClosed()) {
                    byte[] buff = new byte[500];
                    DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
                    multicastSocket.receive(datagramPacket);
                    String message = new String(buff);
                    if (!message.startsWith(nick + ":")) {
                        System.out.println("MultiCast UDP " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
