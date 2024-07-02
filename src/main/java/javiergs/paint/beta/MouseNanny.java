package javiergs.gui.paint.beta;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * MouseNanny listens for mouse events.
 *
 * @author javiergs
 * @version 1.0
 */
public class MouseNanny implements MouseListener {
	
	public void mouseClicked(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("Mouse Pressed: " + x + ", " + y);
	}
	
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("Mouse Released:" + x + ", " + y);
	}
}
