package javiergs.gui.paint.lambda;

import javax.swing.*;
import java.awt.*;

/**
 * The Frame class represents the main application window.
 */
public class Main extends JFrame {
	
	public static void main(String[] args) {
		Main app = new Main();
		app.setSize(1200, 600);
		app.setTitle("Fix this Paint App");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setResizable(false);
		app.setVisible(true);
	}
	
	public Main() {
		JPanel drawPanel = new DrawPanel();
		Officer officer = Officer.getInstance();
		officer.setDrawPanel(drawPanel);
		MouseController ml = new MouseController();
		officer.getDrawPanel().addMouseListener(ml);
		officer.getDrawPanel().addMouseMotionListener(ml);
		TextPanel textPanel = new TextPanel();
		officer.addPropertyChangeListener(textPanel);
		setLayout(new BorderLayout());
		add(drawPanel, BorderLayout.CENTER);
		add(textPanel, BorderLayout.WEST);
		createMenuBar();
		KeyController kl = new KeyController();
		addKeyListener(kl);
		setFocusable(true);
		requestFocusInWindow();
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem loadMenuItem = new JMenuItem("Load");
		Officer officer = Officer.getInstance();
		newMenuItem.addActionListener(e -> officer.newFile());
		saveMenuItem.addActionListener(e -> officer.saveFile());
		loadMenuItem.addActionListener(e -> officer.loadFile());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		menuBar.add(createEditMenu());
		menuBar.add(createShapesMenu());
		menuBar.add(createColorsMenu());
		JMenu aboutMenu = new JMenu("Help");
		JMenuItem creditsMenuItem = new JMenuItem("About");
		creditsMenuItem.addActionListener(e -> dialogBox());
		aboutMenu.add(creditsMenuItem);
		menuBar.add(aboutMenu);
		setJMenuBar(menuBar);
	}
	
	private JMenu createShapesMenu() {
		JMenu shapesMenu = new JMenu("Shapes");
		JRadioButtonMenuItem rectangleTool = new JRadioButtonMenuItem("Rectangle");
		JRadioButtonMenuItem circleTool = new JRadioButtonMenuItem("Circle");
		JRadioButtonMenuItem arcTool = new JRadioButtonMenuItem("Arc");
		JRadioButtonMenuItem lineTool = new JRadioButtonMenuItem("Line");
		ButtonGroup shapeGroup = new ButtonGroup();
		shapeGroup.add(rectangleTool);
		shapeGroup.add(circleTool);
		shapeGroup.add(arcTool);
		shapeGroup.add(lineTool);
		rectangleTool.setSelected(true);
		Officer officer = Officer.getInstance();
		rectangleTool.addActionListener(e -> officer.setShape("Rectangle"));
		circleTool.addActionListener(e -> officer.setShape("Circle"));
		arcTool.addActionListener(e -> officer.setShape("Arc"));
		lineTool.addActionListener(e -> officer.setShape("Line"));
		shapesMenu.add(rectangleTool);
		shapesMenu.add(circleTool);
		shapesMenu.add(arcTool);
		shapesMenu.add(lineTool);
		return shapesMenu;
	}
	
	private JMenu createColorsMenu() {
		JMenu colorMenu = new JMenu("Color");
		JRadioButtonMenuItem blackColor = new JRadioButtonMenuItem("Black");
		JRadioButtonMenuItem redColor = new JRadioButtonMenuItem("Red");
		JRadioButtonMenuItem yellowColor = new JRadioButtonMenuItem("Yellow");
		JRadioButtonMenuItem greenColor = new JRadioButtonMenuItem("Green");
		JRadioButtonMenuItem blueColor = new JRadioButtonMenuItem("Blue");
		ButtonGroup colorGroup = new ButtonGroup();
		colorGroup.add(blackColor);
		colorGroup.add(redColor);
		colorGroup.add(yellowColor);
		colorGroup.add(greenColor);
		colorGroup.add(blueColor);
		blackColor.setSelected(true);
		Officer officer = Officer.getInstance();
		blackColor.addActionListener(e -> officer.setColor(Color.BLACK));
		redColor.addActionListener(e -> officer.setColor(Color.RED));
		yellowColor.addActionListener(e -> officer.setColor(Color.YELLOW));
		greenColor.addActionListener(e -> officer.setColor(Color.GREEN));
		blueColor.addActionListener(e -> officer.setColor(Color.BLUE));
		colorMenu.add(blackColor);
		colorMenu.add(redColor);
		colorMenu.add(yellowColor);
		colorMenu.add(greenColor);
		colorMenu.add(blueColor);
		return colorMenu;
	}
	
	private JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		JMenuItem undoAction = new JMenuItem("Undo");
		JMenuItem redoAction = new JMenuItem("Redo");
		JMenuItem eraseAction = new JMenuItem("Erase");
		JMenuItem copyAction = new JMenuItem("Copy");
		JMenuItem pasteAction = new JMenuItem("Paste");
		Officer officer = Officer.getInstance();
		undoAction.addActionListener(e -> {
			officer.undoFromStack();
			officer.doSomething();
		});
		redoAction.addActionListener(e -> {
			officer.redoToStack();
			officer.doSomething();
		});
		eraseAction.addActionListener(e -> {
			officer.eraseStack();
			officer.doSomething();
		});
		copyAction.addActionListener(e -> officer.copyShape());
		pasteAction.addActionListener(e -> officer.pasteShape());
		editMenu.add(undoAction);
		editMenu.add(redoAction);
		editMenu.add(eraseAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		return editMenu;
	}
	
	private void dialogBox() {
		JDialog d = new JDialog(this, "About");
		JLabel l = new JLabel();
		l.setText("<html>CSC 307; Summer 2024</html>");
		d.add(l);
		d.setSize(400, 150);
		d.setVisible(true);
	}
 
}