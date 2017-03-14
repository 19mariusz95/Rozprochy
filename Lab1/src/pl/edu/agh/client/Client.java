package pl.edu.agh.client;

import pl.edu.agh.client.listeners.MulticastListener;
import pl.edu.agh.client.listeners.TcpListener;
import pl.edu.agh.client.listeners.UdpListener;
import pl.edu.agh.server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {


    public static final String MULTICAST_IP = "230.1.1.1";
    public static final int MULTICAST_PORT = 12344;
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    private static String nick;
    private static byte[] asciiBytes;

    public static void main(String[] args) throws IOException {

        System.out.println("Enter nick:");
        Scanner scanner = new Scanner(System.in);
        nick = scanner.nextLine();

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(Server.PORT));

        DatagramSocket datagramSocket = new DatagramSocket(socket.getLocalPort());

        openUDPListener(datagramSocket);
        openMultiCast();

        asciiBytes = (nick + ":                    \n" +
                "                 _.-;;-._\n" +
                "          '-..-'|   ||   |\n" +
                "          '-..-'|_.-;;-._|\n" +
                "          '-..-'|   ||   |\n" +
                "    jgs   '-..-'|_.-''-._|").getBytes();

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

        printWriter.write(nick + '\n');
        printWriter.flush();

        new TcpListener(socket.getInputStream()).start();

        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            switch (message) {
                case "M": {
                    InetAddress IPAddress = InetAddress.getByName("localhost");
                    datagramSocket.send(new DatagramPacket(asciiBytes, asciiBytes.length, IPAddress, Server.PORT));
                    break;
                }
                case "N": {
                    InetAddress IPAddress = InetAddress.getByName(MULTICAST_IP);
                    datagramSocket.send(new DatagramPacket(asciiBytes, asciiBytes.length, IPAddress, MULTICAST_PORT));
                    break;
                }
                default:
                    printWriter.write(message + '\n');
                    printWriter.flush();
                    break;
            }
        }

        socket.close();
    }

    private static void openUDPListener(DatagramSocket datagramSocket) {
        new UdpListener(datagramSocket).start();
    }

    private static void openMultiCast() {
        new MulticastListener(nick).start();
    }

}
