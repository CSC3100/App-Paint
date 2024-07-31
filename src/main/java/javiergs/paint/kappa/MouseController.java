package javiergs.gui.paint.kappa;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The MouListener class represents a listener for mouse events.
 */
public class MouseController implements MouseListener, MouseMotionListener {
	
	private int x1;
	private int y1;
	private int xDragStart = -1;
	private int yDragStart = -1;
	private Shape draggingShape = null;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedX = e.getX();
		int selectedY = e.getY();
		Shape selectedShape = null;
		for (Shape s : Officer.getStack()) {
			if (s.contains(selectedX, selectedY)) {
				selectedShape = s;
				break;
			}
		}
		Officer.setSelectedShape(selectedShape);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		boolean found = false;
		for (int i = Officer.getStack().size() - 1; i >= 0; i--) {
			Shape s = Officer.getStack().get(i);
			if (s.contains(x1, y1)) {
				xDragStart = x1;
				yDragStart = y1;
				draggingShape = s;
				Officer.setSelectedShape(s);
				found = true;
				break;
			}
		}
		if (!found) {
			xDragStart = yDragStart = -1;
			draggingShape = null;
			Officer.setSelectedShape(null);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (xDragStart != -1 && yDragStart != -1) {
			xDragStart = yDragStart = -1;
		} else if (e.getX() == x1 && e.getY() == y1) {
			Officer.setSelectedShape(null);
		} else {
			shapeCalculation(e, false);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (xDragStart != -1 && yDragStart != -1) {
			int dx = e.getX() - xDragStart;
			int dy = e.getY() - yDragStart;
			Shape selectedShape = Officer.getSelectedShape();
			if (selectedShape != null) {
				selectedShape.move(dx, dy);
			}
			xDragStart = e.getX();
			yDragStart = e.getY();
			Officer.doSomething();
		} else {
			shapeCalculation(e, true);
		}
	}
	
	private void shapeCalculation(MouseEvent e, boolean isDragging) {
		int x = Math.min(x1, e.getX());
		int y = Math.min(y1, e.getY());
		int w = Math.abs(e.getX() - x1);
		int h = Math.abs(e.getY() - y1);
		boolean flip = e.getY() > y1; // Determine if arc should flip
		String currentShape = Officer.getShape();
		if (isDragging) {
			switch (currentShape) {
				case "Rectangle" -> Officer.setSelectedShape(new Rectangle(Officer.getColor(), x, y, w, h));
				case "Circle" -> Officer.setSelectedShape(new Circle(Officer.getColor(), x, y, w, h));
				case "Arc" -> Officer.setSelectedShape(new Arc(Officer.getColor(), x, y, w, h, flip));
				case "Line" -> Officer.setSelectedShape(new Line(Officer.getColor(), x1, y1, e.getX(), e.getY()));
			}
		} else {
			switch (currentShape) {
				case "Rectangle" -> Officer.pushToStack(new Rectangle(Officer.getColor(), x, y, w, h));
				case "Circle" -> Officer.pushToStack(new Circle(Officer.getColor(), x, y, w, h));
				case "Arc" -> Officer.pushToStack(new Arc(Officer.getColor(), x, y, w, h, flip));
				case "Line" -> Officer.pushToStack(new Line(Officer.getColor(), x1, y1, e.getX(), e.getY()));
			}
			Officer.setSelectedShape(null);
		}
		Officer.doSomething();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}