package smartmirror;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SmartHomeServer {

	ServerSocket server;
	Socket socket;
	int id = 0;

	final int PORT = 1234;

	static ClientThread[] clients = new ClientThread[0];

	public SmartHomeServer() {

		try {
			server = new ServerSocket(PORT);
			while (true) {
				socket = server.accept();
				ClientThread[] c = new ClientThread[clients.length + 1];
				System.arraycopy(clients, 0, c, 0, clients.length);
				c[clients.length] = new ClientThread(socket, id);
				clients = c;
				clients[id].start();
				id++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}