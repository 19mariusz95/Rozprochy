package pl.edu.agh.server.listeners;

import pl.edu.agh.server.ClientHandler;
import pl.edu.agh.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class TcpListener extends Thread {

    private static final Logger LOGGER = Logger.getLogger(TcpListener.class.getName());

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Server.PORT)) {
            while (!serverSocket.isClosed()) {
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                Server.executor.submit(clientHandler);
            }
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
