package pl.edu.agh.server.listeners;

import pl.edu.agh.server.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class UdpListener extends Thread {

    private static final Logger LOGGER = Logger.getLogger(UdpListener.class.getName());

    @Override
    public void run() {
        try (DatagramSocket datagramSocket = new DatagramSocket(Server.PORT)) {
            while (!datagramSocket.isClosed()) {
                byte[] bytes = new byte[500];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                datagramSocket.receive(datagramPacket);
                String message = new String(bytes);
                String[] split = message.split(":", 2);
                String nick = split[0];
                synchronized (Server.clientHandlerMap) {
                    Server.clientHandlerMap.entrySet().stream().filter(a -> !a.getKey().equals(nick))
                            .forEach(a -> {
                                try {
                                    int port = a.getValue().getSocket().getPort();
                                    InetAddress IPAddress = InetAddress.getByName(Server.LOCALHOST);
                                    byte[] newBytes = (message).getBytes();
                                    datagramSocket.send(new DatagramPacket(newBytes, newBytes.length, IPAddress, port));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                }

            }
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
