package smartmirror;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {

    Socket socket;
    BufferedReader reader;
    DataOutputStream writer;

    public void run() {

    	
        try {
            writer = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						try {
							System.out.println(reader.readLine());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}	
				}
			}).start();

            @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
            while (true) {
                String s = scanner.nextLine();
                writer.writeByte(s.charAt(0));
            }

            // socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientThread(Socket socket) {
        System.out.println("Client connected");
    	this.socket = socket;
    }
}