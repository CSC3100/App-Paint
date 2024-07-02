package javiergs.gui.paint.beta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionNanny listens for action events.
 *
 * @author javiergs
 * @version 1.0
 */
public class ActionNanny implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ActionNanny: " + e.getActionCommand());
		// guess what the following three lines do
		if (e.getSource() instanceof JComboBox) {
			JComboBox comboBox = (JComboBox) e.getSource();
			System.out.println("Selected: " + comboBox.getSelectedItem());
		}
	}
	
}