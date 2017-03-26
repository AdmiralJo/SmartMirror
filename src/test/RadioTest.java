package test;

import smartmirror.Radio;

public class RadioTest {
	public static void main(String[] args) throws InterruptedException {
	
		Radio r = new Radio();
		r.play("http://radio.flex.ru:8000/radionami");
		
		Thread.sleep(5000);
		r.stop();
	}
}