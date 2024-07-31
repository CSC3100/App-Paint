package javiergs.gui.paint.lambda;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A listener for mouse events.
 */
public class MouseController implements MouseListener, MouseMotionListener {

	private int x1;
	private int y1;
	private int xDragStart = -1;
	private int yDragStart = -1;
	private Shape currentComponentBaseShape;
	private Component currentComponent;
	private boolean firstSelection = true;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedX = e.getX();
		int selectedY = e.getY();
		Component c = findComponent(selectedX, selectedY);
		if (c != null) {
			if (Officer.getInstance().getBaseShapeComponent() != currentComponentBaseShape) {
				firstSelection = true;
			}
			Officer.getInstance().setSelectedComponent(c);
			Officer.getInstance().setBaseShapeComponent(currentComponentBaseShape);
			if (!firstSelection) {
				Officer.getInstance().pushDecorator();
			} else {
				firstSelection = false;
			}
			currentComponent = c;
		} else {
			Officer.getInstance().setSelectedComponent(null);
			Officer.getInstance().setBaseShapeComponent(null);
		}
		Officer.getInstance().doSomething();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		xDragStart = yDragStart = -1;
		currentComponent = findComponent(x1, y1);
		if (currentComponent != null) {
			xDragStart = x1;
			yDragStart = y1;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (xDragStart != -1 && yDragStart != -1) {
			xDragStart = yDragStart = -1;
		} else {
			shapeCalculation(e, false);
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (xDragStart != -1 && yDragStart != -1) {
			int dx = e.getX() - xDragStart;
			int dy = e.getY() - yDragStart;
			if (currentComponent != null) {
				currentComponent.move(dx, dy);
			}
			xDragStart = e.getX();
			yDragStart = e.getY();
			Officer.getInstance().doSomething();
		} else if (currentComponent == null) {
			shapeCalculation(e, true);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
	private Component findComponent(int x, int y) {
		for (int i = Officer.getInstance().getStack().size() - 1; i >= 0; i--) {
			Component s = Officer.getInstance().getStack().get(i);
			while ((s instanceof DecoratorShape)) {
				s = ((DecoratorShape) s).getComponent();
			}
			if (s instanceof Shape) {
				if (((Shape) s).contains(x, y)) {
					currentComponentBaseShape = (Shape) s;
					return Officer.getInstance().getStack().get(i);
				}
			}
		}
		return null;
	}
	
	private void shapeCalculation(MouseEvent e, boolean isDragging) {
		int x = Math.min(x1, e.getX());
		int y = Math.min(y1, e.getY());
		int w = Math.abs(e.getX() - x1);
		int h = Math.abs(e.getY() - y1);
		boolean flip = e.getY() > y1; // Determine if arc should flip
		Officer officer = Officer.getInstance();
		String currentShapeType = officer.getShape();
		if (isDragging) {
			switch (currentShapeType) {
				case "Rectangle" -> officer.setBox(new Rectangle(officer.getColor(), x, y, w, h));
				case "Circle" -> officer.setBox(new Circle(officer.getColor(), x, y, w, h));
				case "Arc" -> officer.setBox(new Arc(officer.getColor(), x, y, w, h, flip));
				case "Line" -> officer.setBox(new Line(officer.getColor(), x1, y1, e.getX(), e.getY()));
			}
		} else {
			switch (currentShapeType) {
				case "Rectangle" -> officer.pushToStack(new Rectangle(officer.getColor(), x, y, w, h));
				case "Circle" -> officer.pushToStack(new Circle(officer.getColor(), x, y, w, h));
				case "Arc" -> officer.pushToStack(new Arc(officer.getColor(), x, y, w, h, flip));
				case "Line" -> {
					if (x1 != e.getX() || y1 != e.getY()) { // Ensure line has non-zero length
						officer.pushToStack(new Line(officer.getColor(), x1, y1, e.getX(), e.getY()));
					}
				}
			}
			officer.setBox(null);
		}
		officer.doSomething();
	}
 
}