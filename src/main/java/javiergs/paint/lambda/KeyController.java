package javiergs.gui.paint.lambda;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class implements key listeners to handle keyboard events
 */
public class KeyController implements KeyListener {
	
	@Override
	public void keyPressed(KeyEvent e) {
		Officer officer = Officer.getInstance();
		if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
			officer.copyShape();
		}
		if ((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
			officer.pasteShape();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
 
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}