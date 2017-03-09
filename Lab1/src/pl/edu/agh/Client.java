package pl.edu.agh;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(Server.PORT));

        DatagramSocket datagramSocket = new DatagramSocket(socket.getLocalPort());

        openUDPListener(datagramSocket);
        openMultiCast();

        System.out.println("Enter nick:");
        Scanner scanner = new Scanner(System.in);
        String nick = scanner.nextLine();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

        printWriter.write(nick + '\n');
        printWriter.flush();

        Scanner finalScanner = new Scanner(socket.getInputStream());
        new Thread(() -> {
            while (finalScanner.hasNextLine()) {
                System.out.println("TCP " + finalScanner.nextLine());
            }

        }).start();

        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            if (message.equals("M") || message.equals("N")) {
                InetAddress IPAddress = InetAddress.getByName("localhost");

                byte[] bytes = (nick + ":" + message + ":                    \n" +
                        "                 _.-;;-._\n" +
                        "          '-..-'|   ||   |\n" +
                        "          '-..-'|_.-;;-._|\n" +
                        "          '-..-'|   ||   |\n" +
                        "    jgs   '-..-'|_.-''-._|").getBytes();
                datagramSocket.send(new DatagramPacket(bytes, bytes.length, IPAddress, Server.PORT));
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
                while (true) {
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
                MulticastSocket multicastSocket = new MulticastSocket(Server.MULTICAST_PORT);
                multicastSocket.joinGroup(InetAddress.getByName(Server.MULTICAST_IP));
                while (true) {
                    byte[] buff = new byte[500];
                    DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
                    multicastSocket.receive(datagramPacket);
                    String message = new String(buff);
                    System.out.println("MultiCast UDP " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
