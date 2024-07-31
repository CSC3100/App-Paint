package javiergs.gui.paint.lambda;

import java.awt.*;
import java.util.Objects;

/**
 * The Shape class is an abstract class that represents a shape.
 */
public abstract class Shape implements Component {
	
	protected Color color;
	protected int x, y, w, h;
	
	public Shape() {
		this.color = Color.BLACK;
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
		
	}
	
	public Shape(Color color, int x, int y, int w, int h) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	@Override
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Shape shape)) return false;
		return x == shape.x && y == shape.y && w == shape.w && h == shape.h && Objects.equals(color, shape.color);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(color, x, y, w, h);
	}
	
	public Component nextDecorator() {
		DecoratorEye eyes = new DecoratorEye();
		eyes.setComponent(this);
		return eyes;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	public abstract boolean contains(int x, int y);
	
	public abstract void drawShape(Graphics g);
 
	public abstract Component clone();
	
}