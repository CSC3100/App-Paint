package javiergs.gui.paint.gamma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionNanny listens for action events.
 * Send information to Officer.
 *
 * @author javiergs
 * @version 2.0
 */
public class ActionNanny implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox comboBox = (JComboBox) e.getSource();
			if (comboBox.getSelectedItem().equals("Black")) {
				Officer.setColor(Color.BLACK);
			} else if (comboBox.getSelectedItem().equals("Red")) {
				Officer.setColor(Color.RED);
			} else if (comboBox.getSelectedItem().equals("Blue")) {
				Officer.setColor(Color.BLUE);
			} else if (comboBox.getSelectedItem().equals("Green")) {
				Officer.setColor(Color.GREEN);
			} else if (comboBox.getSelectedItem().equals("Yellow")) {
				Officer.setColor(Color.YELLOW);
			} else if (comboBox.getSelectedItem().equals("Orange")) {
				Officer.setColor(Color.ORANGE);
			} else if (comboBox.getSelectedItem().equals("Pink")) {
				Officer.setColor(Color.PINK);
			}
		} else if (e.getSource() instanceof JRadioButton) {
			Officer.setShape(e.getActionCommand());
		}
	}
	
}