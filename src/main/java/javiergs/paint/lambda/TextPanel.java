package javiergs.gui.paint.lambda;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Stack;

/**
 * The TextPanel class represents the panel that displays the text data of the shapes.
 */
public class TextPanel extends JPanel implements PropertyChangeListener {
	
	private final JTextArea textArea;
	
	public TextPanel() {
		setPreferredSize(new Dimension(250, 600));
		setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		this.textArea = textArea;
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JLabel label = new JLabel("Shape Data", JLabel.CENTER);
		add(label, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("shapeChanges")) {
			Stack<Component> shapes = (Stack<Component>) evt.getNewValue();
			clearText();
			for (Component shape : shapes) {
				while ((shape instanceof DecoratorShape)) {
					shape = ((DecoratorShape) shape).getComponent();
				}
				appendText(shape.toString());
			}
		}
	}
	
	private void clearText() {
		textArea.setText("");
	}
	
	private void appendText(String text) {
		textArea.append(text);
	}
	
}