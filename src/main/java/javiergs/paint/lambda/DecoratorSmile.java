package javiergs.gui.paint.lambda;

import java.awt.*;

/**
 * The DecoratorShapeSmile class represents a smile decoration.
 */
public class DecoratorSmile extends DecoratorShape {
	
	private int x, y, w, h;
	
	@Override
	public void drawShape(Graphics g) {
		if (super.getComponent() != null) {
			x = component.getX();
			y = component.getY();
			w = component.getW();
			h = component.getH();
		}
		super.drawShape(g);
		addMouth(g);
	}
	
	private void addMouth(Graphics g) {
		if (component != null) {
			int mouthWidth = w / 3;
			int mouthHeight = h / 4;
			int mouthX = x + (w - mouthWidth) / 2;
			int mouthY = y + (2 * h / 3);
			
			g.setColor(Color.DARK_GRAY);
			g.fillOval(mouthX, mouthY, mouthWidth, mouthHeight);
		}
	}
	
	public Component nextDecorator() {
		DecoratorHat hat = new DecoratorHat();
		hat.setComponent(this);
		return hat;
	}
	
	@Override
	public Component clone() {
		DecoratorSmile newMouth = new DecoratorSmile();
		newMouth.setComponent(this.getComponent().clone());
		return newMouth;
	}
	
}
