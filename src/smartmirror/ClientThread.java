package smartmirror;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {

	Socket socket;
	DataOutputStream writer;
	int id;
	boolean b = false;

	public void run() {

		try {

			writer = new DataOutputStream(socket.getOutputStream());
			writer.writeBytes("0\n");

			String[] modules2 = new String[GUI.modules.length + 1];
			System.arraycopy(GUI.modules, 0, modules2, 0, GUI.modules.length);
			modules2[GUI.modules.length] = "Modul " + id + " (aus)";
			GUI.modules = modules2;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ClientThread(Socket socket, int id) {
		System.out.println("Client connected");
		this.socket = socket;
		this.id = id;
	}

	public void changeState(int i) {

		if (i == id) {

			try {

				char c = GUI.menu.getText().charAt(6);

				if (b) {
					writer.writeBytes("0\n");
					GUI.modules[id + 1] = "Modul " + id + " (aus)";

					String[] m = GUI.modules.clone();

					while (m[1].charAt(6) != c) {

						String overfloat = m[0];
						for (int k = 0; k < m.length; k++)
							if (k < m.length - 1)
								m[k] = m[k + 1];
						m[m.length - 1] = overfloat;
					}

					GUI.menu.setItems(m);
					b = false;
				} else {
					writer.writeBytes("1\n");
					GUI.modules[id + 1] = "Modul " + id + " (ein)";

					String[] m = GUI.modules.clone();

					while (m[1].charAt(6) != c) {

						String overfloat = m[0];
						for (int k = 0; k < m.length; k++)
							if (k < m.length - 1)
								m[k] = m[k + 1];
						m[m.length - 1] = overfloat;
					}

					GUI.menu.setItems(m);

					b = true;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}