package javiergs.gui.paint.lambda;

import java.awt.*;
import java.io.*;

/**
 * The Rectangle class represents a rectangle shape.
 */
public class Rectangle extends Shape implements Serializable {
	
	public Rectangle(Color color, int x, int y, int w, int h) {
		super(color, x, y, w, h);
	}
	
	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(color);
		g.fillRect(x, y, w, h);
		if (Officer.getInstance().getBaseShapeComponent() != null && Officer.getInstance().getBaseShapeComponent().equals(this)) {
			g2d.setColor(Color.MAGENTA);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawRect(x, y, w, h);
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
	public Component clone() {
		return new Rectangle(this.color, this.x, this.y, this.w, this.h);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Shape: Rectangle\n");
		sb.append("Color: R = ").append(color.getRed())
			.append(" | G = ").append(color.getGreen())
			.append(" | B = ").append(color.getBlue()).append("\n");
		sb.append("Position: X = ").append(x)
			.append(" | Y = ").append(y).append("\n");
		sb.append("Size: Width = ").append(w)
			.append(" | Height = ").append(h).append("\n\n");
		return sb.toString();
	}
	
}
