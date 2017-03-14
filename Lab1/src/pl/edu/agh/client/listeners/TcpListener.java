package pl.edu.agh.client.listeners;

import java.io.InputStream;
import java.util.Scanner;

public class TcpListener extends Thread {
    private final Scanner tcpScanner;

    public TcpListener(InputStream inputStream) {
        this.tcpScanner = new Scanner(inputStream);
    }

    @Override
    public void run() {
        while (tcpScanner.hasNextLine()) {
            System.out.println("TCP " + tcpScanner.nextLine());
        }

    }
}
