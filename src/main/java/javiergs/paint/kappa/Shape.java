package javiergs.gui.paint.kappa;

import java.awt.*;

/**
 * The Shape class represents a drawable shape.
 */
public abstract class Shape {
	
	protected Color color;
	protected int x, y, w, h;
	protected boolean selected = false;
	
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
	
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void drawSelectionHighlight(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.CYAN);
		g2d.setStroke(new BasicStroke(3));
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public abstract boolean contains(int x, int y);
	
	public abstract void drawShape(Graphics g);
 
	@Override
	public abstract Shape clone();
 
}