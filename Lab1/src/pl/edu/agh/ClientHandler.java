package pl.edu.agh;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    private final Scanner scanner;
    private final PrintWriter printWriter;
    private Socket socket;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;

        scanner = new Scanner(socket.getInputStream());
        printWriter = new PrintWriter(socket.getOutputStream(), true);

    }

    @Override
    public void run() {
        String nick = scanner.nextLine();
        Server.registerUser(nick, this);
        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            Server.sendToAll(nick, message);
        }
    }

    public void send(String message) {
        printWriter.write(message + '\n');
        printWriter.flush();
    }

    public Socket getSocket() {
        return socket;
    }
}
