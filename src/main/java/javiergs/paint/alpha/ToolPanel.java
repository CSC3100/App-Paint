package javiergs.gui.paint.alpha;

import javax.swing.*;
import java.awt.*;

/**
 * ToolPanel creates a panel with a color menu and three radio buttons.
 *
 * @author javiergs
 * @version 1.0
 */
public class ToolPanel extends JPanel {
	
	public ToolPanel() {
		String[] colors = {
			"Black", "Red", "Blue", "Green", "Yellow", "Orange", "Pink"
		};
		JComboBox colorMenu = new JComboBox(colors);
		JRadioButton rectangleRadio = new JRadioButton("Rectangle");
		JRadioButton circleRadio = new JRadioButton("Circle");
		JRadioButton arcRadio = new JRadioButton("Arc");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rectangleRadio);
		buttonGroup.add(circleRadio);
		buttonGroup.add(arcRadio);
		setLayout(new GridLayout(7, 1));
		add(colorMenu);
		add(rectangleRadio);
		add(circleRadio);
		add(arcRadio);
	}
	
}