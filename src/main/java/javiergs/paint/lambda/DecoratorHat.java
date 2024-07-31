package javiergs.gui.paint.lambda;

import java.awt.*;

/**
 * The DecoratorHat class represents a hat decoration.
 */
public class DecoratorHat extends DecoratorShape {
	
	private int x, y, w, h;
	
	@Override
	public void drawShape(Graphics g) {
		if (component != null) {
			x = component.getX();
			y = component.getY();
			w = component.getW();
			h = component.getH();
		}
		super.drawShape(g);
		addHat(g);
	}
	
	private void addHat(Graphics g) {
		if (component != null) {
			int hatWidth = w;
			int hatHeight = h / 3;
			int hatX = x;
			int hatY = y - hatHeight / 3;
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect(hatX, hatY, hatWidth, hatHeight);
			
			int brimHeight = hatHeight / 3;
			int brimY = hatY + hatHeight - brimHeight;
			g.fillRect(hatX - hatWidth / 4, brimY, hatWidth + hatWidth / 2, brimHeight);
		}
	}
	
	public Component nextDecorator() {
		return Officer.getInstance().getBaseShapeComponent();
	}
	
	@Override
	public Component clone() {
		DecoratorHat newHat = new DecoratorHat();
		newHat.setComponent(this.getComponent().clone());
		return newHat;
	}
	
}