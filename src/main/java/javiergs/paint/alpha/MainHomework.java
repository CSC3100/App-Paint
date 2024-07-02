package javiergs.gui.paint.alpha;

import javax.swing.*;
import java.awt.*;

/**
 * MainHomework creates a frame and adds three panels to it.
 * The panels are DrawPanel, ToolPanel, and StatusPanel.
 * The main frame is set to be not resizable and visible.
 *
 * @author javiergs
 * @version 1.0
 */
public class MainHomework extends JFrame {
	
	public static void main(String[] args) {
		MainHomework app = new MainHomework();
		app.setSize(800, 600);
		app.setTitle("First Attempt for a Drawing Application");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setResizable(false);
		app.setVisible(true);
	}
	
	public MainHomework() {
		JPanel drawPanel = new DrawPanel();
		JPanel toolPanel = new ToolPanel();
		JPanel statusPanel = new StatusPanel();
		setLayout(new BorderLayout());
		add(toolPanel, BorderLayout.WEST);
		add(statusPanel, BorderLayout.SOUTH);
		add(drawPanel, BorderLayout.CENTER);
	}
	
}
