package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket server;
    Socket socket;

    final int PORT = 1234;

    public Server() {

        try {
            server = new ServerSocket(PORT);
            while (true) {
                socket = server.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}