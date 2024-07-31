package javiergs.gui.paint.lambda;

import java.awt.*;

/**
 * The EyeDecoration class represents an eye decoration.
 */
public class DecoratorEye extends DecoratorShape {
	
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
		addEyes(g);
	}
	
	private void addEyes(Graphics g) {
		if (component != null) {
			int eyeWidth = w / 4;
			int eyeHeight = h / 4;
			int eyeY = y + h / 3;
			
			// Left eye
			int leftEyeX = x + w / 5;
			g.setColor(Color.WHITE);
			g.fillOval(leftEyeX, eyeY, eyeWidth, eyeHeight);
			g.setColor(Color.GRAY);
			g.fillOval(leftEyeX + (3 * eyeWidth / 6), eyeY + eyeHeight / 3, eyeWidth / 3, eyeHeight / 3);
			
			// Right eye
			int rightEyeX = x + (3 * w / 5) - eyeWidth / 2;
			g.setColor(Color.WHITE);
			g.fillOval(rightEyeX, eyeY, eyeWidth, eyeHeight);
			g.setColor(Color.GRAY);
			g.fillOval(rightEyeX + (eyeWidth / 5), eyeY + eyeHeight / 3, eyeWidth / 3, eyeHeight / 3);
		}
	}
	
	public Component nextDecorator() {
		if (Officer.getInstance().getBaseShapeComponent() instanceof Arc) {
			DecoratorHat hat = new DecoratorHat();
			hat.setComponent(this);
			return hat;
		} else {
			DecoratorSmile mouth = new DecoratorSmile();
			mouth.setComponent(this);
			return mouth;
		}
	}
	
	@Override
	public Component clone() {
		DecoratorEye newEyes = new DecoratorEye();
		newEyes.setComponent(this.getComponent().clone());
		return newEyes;
	}
 
}