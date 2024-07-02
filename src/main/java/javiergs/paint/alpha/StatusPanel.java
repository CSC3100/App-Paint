package javiergs.gui.paint.alpha;

import javax.swing.*;

/**
 * StatusPanel creates a panel with two buttons: Undo and Erase.
 *
 * @author javiergs
 * @version 1.0
 */
public class StatusPanel extends JPanel {
	
	public StatusPanel() {
		JButton button1 = new JButton("Undo");
		JButton button2 = new JButton("Erase");
		add(button1);
		add(button2);
	}

}