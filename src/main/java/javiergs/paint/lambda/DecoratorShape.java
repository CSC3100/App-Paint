package javiergs.gui.paint.lambda;

import java.awt.*;
import java.io.Serializable;

/**
 * The DecoratorShape class represents a shape decorator.
 */
public abstract class DecoratorShape implements Component, Serializable {
	
	protected Component component;
	
	public abstract Component clone();
	
	public void setComponent(Component component) {
		this.component = component;
	}
	
	public Component getComponent() {
		return component;
	}
	
	@Override
	public void drawShape(Graphics g) {
		if (component != null) {
			component.drawShape(g);
		}
	}
	
	@Override
	public int getX() {
		return component.getX();
	}
	
	@Override
	public int getY() {
		return component.getY();
	}
	
	@Override
	public int getW() {
		return component.getW();
	}
	
	@Override
	public int getH() {
		return component.getH();
	}
	
	@Override
	public void move(int dx, int dy) {
		if (component != null) {
			component.move(dx, dy);
		}
	}
	
}