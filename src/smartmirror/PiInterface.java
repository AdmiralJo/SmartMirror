package smartmirror;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import essentials.Essentials;

public class PiInterface {

	final GpioController controller = GpioFactory.getInstance();
	final GpioPinDigitalOutput light_pin = controller.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MirrorLight",
			PinState.HIGH);
	final GpioPinDigitalInput up = controller.provisionDigitalInputPin(RaspiPin.GPIO_15, PinPullResistance.PULL_DOWN),
			down = controller.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_DOWN),
			enter = controller.provisionDigitalInputPin(RaspiPin.GPIO_16, PinPullResistance.PULL_DOWN);

	boolean b = false;

	public PiInterface() {

		up.setShutdownOptions(true);
		down.setShutdownOptions(true);
		enter.setShutdownOptions(true);

		switchLight();

		up.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

				if (event.getState().toString().equals("HIGH")) {

					GUI.i = 500;
					if (GUI.displayMenu)
						GUI.menu.next();
				}
			}
		});

		down.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

				if (event.getState().toString().equals("HIGH")) {

					GUI.i = 500;
					if (GUI.displayMenu)
						GUI.menu.back();
				}
			}
		});

		enter.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

				if (event.getState().toString().equals("HIGH")) {

					GUI.menu.setBounds((int) ((GUI.frame.getWidth() - GUI.frame.getHeight() / 2) / 2),
							(int) (GUI.frame.getHeight() / 5), (int) (GUI.frame.getHeight() / 2),
							(int) (GUI.frame.getHeight() / 5));

					GUI.i = 500;

					if (GUI.displayMenu) {

						if (GUI.menu.getText().equals("zurück"))
							GUI.menu.setItems(GUI.items);
						else {

							if (GUI.menu.getText().equals("Radio"))
								GUI.menu.setItems(GUI.radioKeys.clone());

							else if (Essentials.isIncluded(GUI.radioKeys, GUI.menu.getText())) {
								GUI.radio.stop();
								GUI.radio.play(GUI.radios.getProperty(GUI.menu.getText()));
							}

							if (GUI.menu.getText().equals("Licht einschalten")) {
								GUI.items[1] = "Licht ausschalten";
								String[] m = GUI.items.clone();
								while (!m[1].startsWith("Licht")) {

									String overfloat = m[0];
									for (int k = 0; k < m.length; k++)
										if (k < m.length - 1)
											m[k] = m[k + 1];
									m[m.length - 1] = overfloat;
								}
								GUI.menu.setItems(m);
//								b = false;
//								GUI.piInterface.switchLight();
								
								GUI.piInterface.turnLighOn();
							} else if (GUI.menu.getText().equals("Licht ausschalten")) {
								GUI.items[1] = "Licht einschalten";
								String[] m = GUI.items.clone();
								while (!m[1].startsWith("Licht")) {

									String overfloat = m[0];
									for (int k = 0; k < m.length; k++)
										if (k < m.length - 1)
											m[k] = m[k + 1];
									m[m.length - 1] = overfloat;
								}
								GUI.menu.setItems(m);
//								b = true;
//								GUI.piInterface.switchLight();
								
								GUI.piInterface.turnLighOff();
							}

							if (GUI.menu.getText().equals("Smart Home"))
								GUI.menu.setItems(GUI.modules.clone());

							else if (Essentials.isIncluded(GUI.modules, GUI.menu.getText())) {

								for (ClientThread thread : SmartHomeServer.clients) {
									thread.changeState(Integer.parseInt(String.valueOf(GUI.menu.getText().charAt(6))));
								}
							}

							if (GUI.menu.getText().equals("Einstellungen")) {
								GUI.menu.setItems(GUI.settings);
							}

							else if (GUI.menu.getText().equals("Newsbar anzeigen")) {
								if (GUI.news.isVisible())
									GUI.news.setVisible(false);
								else
									GUI.news.setVisible(true);
							}
						}

					} else {

						GUI.showMenu(true);
						GUI.displayMenu = true;

						new Thread(new Runnable() {

							@Override
							public void run() {

								while (GUI.i > 0) {
									--GUI.i;
									try {
										Thread.sleep(10);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}

								GUI.menu.setItems(GUI.items.clone());
								GUI.showMenu(false);
								GUI.displayMenu = false;
							}
						}).start();
					}
				}
			}
		});
	}

	public void turnLighOn(){
		light_pin.high();
		b = true;
		System.out.println("Lights turned on");
	}
	
	public void turnLighOff(){
		light_pin.low();
		b = false;
		System.out.println("Lights turned off");
	}
	
	public void switchLight() {
		if (!b) {
			light_pin.high();
			b = true;
			System.out.println("Lights switched on");
		} else {
			light_pin.low();
			b = false;
			System.out.println("Lights switched off");
		}
	}
}
