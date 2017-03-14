package pl.edu.agh.client.listeners;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Logger;

public class UdpListener extends Thread {
    private static Logger LOGGER = Logger.getLogger(UdpListener.class.getName());
    private final DatagramSocket datagramSocket;

    public UdpListener(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        try {
            while (!datagramSocket.isClosed()) {
                byte[] buff = new byte[500];
                DatagramPacket datagramPacket = new DatagramPacket(buff, buff.length);
                datagramSocket.receive(datagramPacket);
                String message = new String(buff);
                System.out.println("UDP " + message);
            }
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        } finally {
            datagramSocket.close();
        }
    }
}
