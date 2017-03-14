package pl.edu.agh.server;

import pl.edu.agh.server.listeners.TcpListener;
import pl.edu.agh.server.listeners.UdpListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int PORT = 12345;
    public static final ExecutorService executor = Executors.newFixedThreadPool(100);
    public static final Map<String, ClientHandler> clientHandlerMap = new HashMap<>();
    public static final String LOCALHOST = "localhost";

    public static void main(String[] args) throws IOException {
        openTCP();
        openUDP();
    }

    private static void openUDP() {
        new UdpListener().start();
    }

    private static void openTCP() {
        new TcpListener().start();
    }

    public static boolean registerUser(String name, ClientHandler clientHandler) {
        clientHandlerMap.put(name, clientHandler);
        System.out.println("Registered " + name);
        return true;
    }

}
