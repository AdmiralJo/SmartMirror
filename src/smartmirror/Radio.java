package smartmirror;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Radio {

	Player player;

	public Radio() {
	}

	public void play(String url) {

		if (url == null)
			stop();
		else {
			new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						URLConnection connection = new URL(url).openConnection();
						connection.connect();
						player = new Player(connection.getInputStream());
						player.play();
					} catch (IOException | JavaLayerException e) {
						e.printStackTrace();
					}
					;
				}
			}).start();
		}
	}

	public void stop() {
		if (player != null)
			player.close();
	}
}
