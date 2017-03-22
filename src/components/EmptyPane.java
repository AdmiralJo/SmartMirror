package components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class EmptyPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public EmptyPane() {
		setBackground(Color.black);
		setPreferredSize(new Dimension(100, 0));
	}
}
