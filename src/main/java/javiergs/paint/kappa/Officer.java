package javiergs.gui.paint.kappa;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The Officer class represents the officer in charge of the drawing application.
 */
public class Officer {
 
	private static final Stack<Shape> shapes = new Stack<>();
	private static final Stack<Shape> redoShapes = new Stack<>();
	private static final Stack<List<Shape>> clearedShapes = new Stack<>();
	private static final Stack<List<Shape>> redoClearedShapes = new Stack<>();
	private static JPanel drawPanel;
	private static Color color;
	private static String shape;
	private static Shape selectedShape;
	private static Shape copiedShape;
	private static boolean savePerformed = false;
	private static boolean lastActionWasEraseAll = false;
	private static int pasteOffsetX = 10;
	private static int pasteOffsetY = 10;
	private static Shape lastPastedShape = null;
	private static final List<Runnable> copyListeners = new ArrayList<>();
	private static final List<Runnable> undoRedoListeners = new ArrayList<>();
	private static final List<Runnable> selectionListeners = new ArrayList<>();
	
	public static JPanel getDrawPanel() {
		return drawPanel;
	}
	
	public static void setDrawPanel(JPanel drawPanel) {
		Officer.drawPanel = drawPanel;
	}
	
	public static Color getColor() {
		return color == null ? Color.black : color;
	}
	
	public static void setColor(Color color) {
		Officer.color = color;
		if (selectedShape != null) {
			selectedShape.color = color;
		}
		doSomething();
	}
	
	public static String getShape() {
		return shape == null ? "Rectangle" : shape;
	}
	
	public static void setShape(String shape) {
		Officer.shape = shape;
		setSelectedShape(null);
	}
	
	public static void doSomething() {
		drawPanel.repaint();
	}
	
	public static Stack<Shape> getStack() {
		return shapes;
	}
	
	public static Stack<Shape> getRedoStack() {
		return redoShapes;
	}
	
	public static Stack<List<Shape>> getClearedShapes() {
		return clearedShapes;
	}
	
	public static boolean wasLastActionEraseAll() {
		return lastActionWasEraseAll;
	}
	
	public static void pushToStack(Shape shape) {
		Officer.shapes.add(shape);
		redoShapes.clear();
		redoClearedShapes.clear();
		lastActionWasEraseAll = false;
		savePerformed = false;
		notifyUndoRedoListeners();
	}
	
	public static void popFromStack() {
		if (!shapes.isEmpty()) {
			Shape shapeToUndo = shapes.pop();
			redoShapes.add(shapeToUndo);
			setSelectedShape(null);
			lastActionWasEraseAll = false;
		} else if (lastActionWasEraseAll && !clearedShapes.isEmpty()) {
			List<Shape> shapesToRestore = clearedShapes.pop();
			redoClearedShapes.push(new ArrayList<>(shapes));
			shapes.addAll(shapesToRestore);
			lastActionWasEraseAll = false;
		}
		notifyUndoRedoListeners();
	}
	
	public static void redoToStack() {
		if (!redoShapes.isEmpty() && !lastActionWasEraseAll) {
			Shape shapeToRedo = redoShapes.pop();
			shapes.add(shapeToRedo);
			setSelectedShape(shapeToRedo); // Select the redone shape
		} else if (lastActionWasEraseAll && !redoClearedShapes.isEmpty()) {
			List<Shape> shapesToRestore = redoClearedShapes.pop();
			shapes.clear();
			shapes.addAll(shapesToRestore);
			lastActionWasEraseAll = true;
		}
		notifyUndoRedoListeners();
	}
	
	public static void clearStack() {
		if (!shapes.isEmpty()) {
			List<Shape> shapesToClear = new ArrayList<>(shapes);
			clearedShapes.add(shapesToClear);
			shapes.clear();
			setSelectedShape(null);
			lastActionWasEraseAll = true;
			notifyUndoRedoListeners();
		}
	}
	
	public static Shape getSelectedShape() {
		return selectedShape;
	}
	
	public static void setSelectedShape(Shape shape) {
		if (selectedShape != null) {
			selectedShape.setSelected(false);
		}
		selectedShape = shape;
		if (selectedShape != null) {
			selectedShape.setSelected(true);
		}
		pasteOffsetX = 10;
		pasteOffsetY = 10;
		lastPastedShape = null;
		doSomething();
		notifySelectionListeners();
	}
	
	public static void copyShape() {
		if (selectedShape != null) {
			copiedShape = selectedShape.clone();
			notifyCopyListeners();
		}
	}
	
	public static boolean hasCopiedShape() {
		return copiedShape != null;
	}
	
	public static boolean hasSelectedShape() {
		return selectedShape != null;
	}
	
	public static void pasteShape() {
		if (copiedShape != null) {
			Shape newShape = copiedShape.clone();
			int newX, newY;
			if (lastPastedShape != null) {
				newX = lastPastedShape.getX() + pasteOffsetX;
				newY = lastPastedShape.getY() + pasteOffsetY;
			} else {
				newX = copiedShape.getX() + pasteOffsetX;
				newY = copiedShape.getY() + pasteOffsetY;
			}
			
			newShape.move(newX - newShape.getX(), newY - newShape.getY());
			pushToStack(newShape);
			setSelectedShape(newShape);
			
			lastPastedShape = newShape;
			pasteOffsetX += 10;
			pasteOffsetY += 10;
		}
	}
	
	public static void newFile() {
		if (!shapes.isEmpty() || !redoShapes.isEmpty() || !clearedShapes.isEmpty()) {
			if (!savePerformed) {
				int wantSave = JOptionPane.showConfirmDialog(drawPanel, "You have not saved this file. Would you like to save?", null, JOptionPane.YES_NO_OPTION);
				if (wantSave == JOptionPane.YES_OPTION) {
					saveFile();
				} else if (wantSave == JOptionPane.NO_OPTION) {
					shapes.clear();
					redoShapes.clear();
					clearedShapes.clear();
					redoClearedShapes.clear();
					selectedShape = null;
					copiedShape = null;
					pasteOffsetX = 10;
					pasteOffsetY = 10;
					lastPastedShape = null;
					lastActionWasEraseAll = false;
					doSomething();
					notifyUndoRedoListeners();
					notifyCopyListeners();
					notifySelectionListeners();
				}
			} else {
				shapes.clear();
				redoShapes.clear();
				clearedShapes.clear();
				redoClearedShapes.clear();
				selectedShape = null;
				copiedShape = null;
				pasteOffsetX = 10;
				pasteOffsetY = 10;
				lastPastedShape = null;
				lastActionWasEraseAll = false;
				doSomething();
				notifyUndoRedoListeners();
				notifyCopyListeners();
				notifySelectionListeners();
			}
		}
	}
	
	public static void saveFile() {
		String fileName = JOptionPane.showInputDialog(drawPanel, "Enter file name (without extension):", "Save File", JOptionPane.PLAIN_MESSAGE);
		if (fileName != null) {
			if (!fileName.endsWith(".ser")) {
				fileName += ".ser";
			}
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
				oos.writeObject(shapes);
				savePerformed = true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(drawPanel, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
 
	public static void loadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Load File");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files", "ser");
		fileChooser.setFileFilter(filter);
		int userSelection = fileChooser.showOpenDialog(drawPanel);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileName = fileChooser.getSelectedFile();
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
				shapes.clear();
				redoShapes.clear();
				redoClearedShapes.clear();
				Stack<Shape> loadedShapes = (Stack<Shape>) ois.readObject();
				shapes.addAll(loadedShapes);
				doSomething();
				notifyUndoRedoListeners();
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(drawPanel, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (userSelection == JFileChooser.CANCEL_OPTION) {
			System.out.println("Load operation canceled by user.");
		} else if (userSelection == JFileChooser.ERROR_OPTION) {
			System.err.println("Error in file chooser dialog.");
		}
	}
	
	public static void addSelectionListener(Runnable listener) {
		selectionListeners.add(listener);
	}
	
	private static void notifySelectionListeners() {
		for (Runnable listener : selectionListeners) {
			listener.run();
		}
	}
	
	public static void addCopyListener(Runnable listener) {
		copyListeners.add(listener);
	}
	
	private static void notifyCopyListeners() {
		for (Runnable listener : copyListeners) {
			listener.run();
		}
	}
	
	public static void addUndoRedoListener(Runnable listener) {
		undoRedoListeners.add(listener);
	}
	
	private static void notifyUndoRedoListeners() {
		for (Runnable listener : undoRedoListeners) {
			listener.run();
		}
	}
 
}