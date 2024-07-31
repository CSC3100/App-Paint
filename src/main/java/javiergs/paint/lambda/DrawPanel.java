package javiergs.gui.paint.lambda;

import javax.swing.*;
import java.awt.*;

/**
 * The DrawPanel class represents a panel where shapes are drawn.
 */
public class DrawPanel extends JPanel {
	
	public DrawPanel() {
		setBackground(new Color(176, 250, 192));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Officer officer = Officer.getInstance();
		super.paintComponent(g);
		for (Component s : officer.getStack()) {
			s.drawShape(g);
		}
		if (officer.getBox() != null) {
			officer.getBox().drawShape(g);
		}
	}
	
}