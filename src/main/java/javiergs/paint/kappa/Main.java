package javiergs.gui.paint.kappa;

import javax.swing.*;
import java.awt.*;

/**
 * The Frame class represents the main frame of the application.
 */
public class Main extends JFrame {
	
	private JMenuItem pasteMenuItem;
	private JMenuItem undoMenuItem;
	private JMenuItem redoMenuItem;
	private JMenuItem copyMenuItem;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.setSize(800, 600);
		main.setTitle("A New Paint App");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setResizable(false);
		main.setVisible(true);
	}
	
	public Main() {
		JPanel drawPanel = new DrawPanel();
		Officer.setDrawPanel(drawPanel);
		MouseController ml = new MouseController();
		Officer.getDrawPanel().addMouseListener(ml);
		Officer.getDrawPanel().addMouseMotionListener(ml);
		setLayout(new BorderLayout());
		add(drawPanel, BorderLayout.CENTER);
		createMenuBar();
		Officer.addCopyListener(this::enablePasteMenuItem);
		Officer.addUndoRedoListener(this::updateUndoRedoMenuItems);
		Officer.addSelectionListener(this::updateCopyMenuItem);
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
		JMenuItem eraseAllMenuItem = new JMenuItem("Erase All");  // Move and rename the erase option
		newMenuItem.addActionListener(e -> Officer.newFile());
		saveMenuItem.addActionListener(e -> Officer.saveFile());
		loadMenuItem.addActionListener(e -> Officer.loadFile());
		eraseAllMenuItem.addActionListener(e -> {
			Officer.clearStack();
			Officer.doSomething();
		});
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		fileMenu.add(eraseAllMenuItem);
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
		rectangleTool.addActionListener(e -> Officer.setShape("Rectangle"));
		circleTool.addActionListener(e -> Officer.setShape("Circle"));
		arcTool.addActionListener(e -> Officer.setShape("Arc"));
		lineTool.addActionListener(e -> Officer.setShape("Line"));
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
		blackColor.addActionListener(e -> Officer.setColor(Color.BLACK));
		redColor.addActionListener(e -> Officer.setColor(Color.RED));
		yellowColor.addActionListener(e -> Officer.setColor(Color.YELLOW));
		greenColor.addActionListener(e -> Officer.setColor(Color.GREEN));
		blueColor.addActionListener(e -> Officer.setColor(Color.BLUE));
		colorMenu.add(blackColor);
		colorMenu.add(redColor);
		colorMenu.add(yellowColor);
		colorMenu.add(greenColor);
		colorMenu.add(blueColor);
		return colorMenu;
	}
	
	private JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		undoMenuItem = new JMenuItem("Undo");
		redoMenuItem = new JMenuItem("Redo");
		copyMenuItem = new JMenuItem("Copy");
		pasteMenuItem = new JMenuItem("Paste");
		undoMenuItem.addActionListener(e -> {
			Officer.popFromStack();
			Officer.doSomething();
		});
		redoMenuItem.addActionListener(e -> {
			Officer.redoToStack();
			Officer.doSomething();
		});
		copyMenuItem.addActionListener(e -> Officer.copyShape());
		pasteMenuItem.addActionListener(e -> Officer.pasteShape());
		pasteMenuItem.setEnabled(false);
		editMenu.add(undoMenuItem);
		editMenu.add(redoMenuItem);
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);
		updateUndoRedoMenuItems();
		updateCopyMenuItem();
		return editMenu;
	}
	
	private void enablePasteMenuItem() {
		pasteMenuItem.setEnabled(Officer.hasCopiedShape());
	}
	
	private void updateUndoRedoMenuItems() {
		undoMenuItem.setEnabled(!Officer.getStack().isEmpty() || !Officer.getClearedShapes().isEmpty());
		redoMenuItem.setEnabled(!Officer.getRedoStack().isEmpty() && !Officer.wasLastActionEraseAll());
	}
	
	private void updateCopyMenuItem() {
		copyMenuItem.setEnabled(Officer.hasSelectedShape());
	}
	
	private void dialogBox() {
		JDialog d = new JDialog(this, "About");
		JLabel l = new JLabel("CSC 307. Summer 2024", SwingConstants.CENTER);
		d.setLayout(new BorderLayout());
		d.add(l, BorderLayout.CENTER);
		d.setSize(400, 150);
		d.setLocationRelativeTo(this); // Center the dialog relative to the frame
		d.setVisible(true);
	}
	
}