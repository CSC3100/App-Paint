package javiergs.gui.paint.kappa;

import java.awt.*;
import java.io.*;

/**
 * The Circle class represents a drawable circle shape.
 */
public class Circle extends Shape implements Serializable {
	
	public Circle(Color color, int x, int y, int w, int h) {
		super(color, x, y, w, h);
	}
	
	@Override
	public void drawShape(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, w, h);
		if (selected) {
			drawSelectionHighlight(g);
		}
	}
	
	@Serial
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(color.getRGB());
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(w);
		out.writeInt(h);
	}
	
	@Serial
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		color = new Color(in.readInt());
		x = in.readInt();
		y = in.readInt();
		w = in.readInt();
		h = in.readInt();
	}
	
	@Override
	public boolean contains(int x, int y) {
		return Math.pow(x - this.x - w / 2, 2) + Math.pow(y - this.y - h / 2, 2) <= Math.pow(w / 2, 2);
	}
	
	@Override
	public Shape clone() {
		return new Circle(this.color, this.x, this.y, this.w, this.h);
	}
	
	@Override
	public void drawSelectionHighlight(Graphics g) {
		super.drawSelectionHighlight(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawOval(x, y, w, h);
	}
	
}