package javiergs.gui.paint.kappa;

import java.awt.*;
import java.io.*;

/**
 * The Rectangle class represents a drawable rectangle shape.
 */
public class Rectangle extends Shape implements Serializable {
	
	public Rectangle(Color color, int x, int y, int w, int h) {
		super(color, x, y, w, h);
	}
	
	@Override
	public void drawShape(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		if (selected) {
			drawSelectionHighlight(g);
		}
	}
	
	@Serial
	private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
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
		return x >= this.x && x <= this.x + w && y >= this.y && y <= this.y + h;
	}
	
	@Override
	public Shape clone() {
		return new Rectangle(this.color, this.x, this.y, this.w, this.h);
	}
	
	@Override
	public void drawSelectionHighlight(Graphics g) {
		super.drawSelectionHighlight(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRect(x, y, w, h);
	}
 
}