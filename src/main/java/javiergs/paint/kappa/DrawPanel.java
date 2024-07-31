package javiergs.gui.paint.kappa;

import javax.swing.*;
import java.awt.*;

/**
 * The DrawPanel class represents a drawable panel that contains shapes.
 */
public class DrawPanel extends JPanel {
	
	public DrawPanel() {
		setBackground(new Color(176, 250, 192));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape s : Officer.getStack()) {
			s.drawShape(g);
		}
		if (Officer.getSelectedShape() != null) {
			Officer.getSelectedShape().drawShape(g);
		}
	}
	
}
