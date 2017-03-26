package smartmirror;

//import com.pi4j.io.gpio.GpioController;
//import com.pi4j.io.gpio.GpioFactory;
//import com.pi4j.io.gpio.GpioPinDigitalOutput;
//import com.pi4j.io.gpio.PinState;
//import com.pi4j.io.gpio.RaspiPin;

public class PiInterface {

	// final GpioController controller;
	// final GpioPinDigitalOutput pin;

	boolean b = false;

	public PiInterface() {

		// controller = GpioFactory.getInstance();
		// pin = controller.provisionDigitalOutputPin(RaspiPin.GPIO_01,
		// "MirrorLight", PinState.LOW);
	}

	public void switchLight() {
		if (!b) {
//			pin.high();
			b = true;
			System.out.println("Lights switched on");
		} else {
//			pin.low();
			b = false;
			System.out.println("Lights switched off");
		}
	}
}
