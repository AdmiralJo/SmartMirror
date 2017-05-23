package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket s = new Socket("127.0.0.1", 1234);
		BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));

		while (true) {

			System.out.println(reader.readLine());
		}
	}
}
