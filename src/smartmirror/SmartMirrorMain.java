package smartmirror;

public class SmartMirrorMain {

	public SmartMirrorMain() {
		new GUI();
		new SmartHomeServer();
	}
	
	public static void main(String[] args) {
		new SmartMirrorMain();
	}
}
