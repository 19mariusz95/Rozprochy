package pl.edu.agh.client.listeners;

import pl.edu.agh.client.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Logger;

public class MulticastListener extends Thread {
    private static final Logger LOGGER = Logger.getLogger(MulticastListener.class.getName());
    private String nick;

    public MulticastListener(String nick) {
        this.nick = nick;
    }

    @Override
    public void run() {
        try (MulticastSocket multicastSocket = new MulticastSocket(Client.MULTICAST_PORT)) {
            multicastSocket.joinGroup(InetAddress.getByName(Client.MULTICAST_IP));
            while (!multicastSocket.isClosed()) {
                byte[] buff = new byte[500];
                DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
                multicastSocket.receive(datagramPacket);
                String message = new String(buff);
                if (!message.startsWith(this.nick + ":")) {
                    System.out.println("MultiCast UDP " + message);
                }
            }
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
