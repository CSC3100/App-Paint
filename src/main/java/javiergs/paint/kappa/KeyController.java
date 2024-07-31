package javiergs.gui.paint.kappa;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyboardListener class represents a listener for keyboard events.
 */
public class KeyController implements KeyListener {
	
	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
			Officer.copyShape();
		}
		if ((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
			Officer.pasteShape();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}