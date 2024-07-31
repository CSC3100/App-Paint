package javiergs.gui.paint.lambda;

import java.awt.*;
import java.io.*;

/**
 * The Line class represents a line shape that can be drawn on the screen.
 */
public class Line extends Shape implements Serializable {
	
	public Line(Color color, int x, int y, int w, int h) {
		super(color, x, y, w, h);
	}
	
	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(7));
		g2d.setColor(color);
		g2d.drawLine(x, y, w, h);
		if (Officer.getInstance().getBaseShapeComponent() != null && Officer.getInstance().getBaseShapeComponent().equals(this)) {
			g2d.setColor(Color.MAGENTA);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawLine(x, y, w, h);
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
		double distance = ptSegDist(this.x, this.y, this.w, this.h, x, y);
		return distance <= 3;
	}
	
	private double ptSegDist(int x1, int y1, int x2, int y2, int px, int py) {
		double dx = x2 - x1;
		double dy = y2 - y1;
		double px1 = px - x1;
		double py1 = py - y1;
		double lengthSquared = dx * dx + dy * dy;
		double projection = (px1 * dx + py1 * dy) / lengthSquared;
		if (projection < 0) {
			dx = px1;
			dy = py1;
		} else if (projection > 1) {
			dx = px - x2;
			dy = py - y2;
		} else {
			dx = px1 - projection * dx;
			dy = py1 - projection * dy;
		}
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	@Override
	public Shape clone() {
		return new Line(this.color, this.x, this.y, this.w, this.h);
	}
	
	@Override
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		this.w += dx;
		this.h += dy;
	}
	
	@Override
	public Component nextDecorator() {
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Shape: Line\n");
		sb.append("Color: R = ").append(color.getRed())
			.append(" | G = ").append(color.getGreen())
			.append(" | B = ").append(color.getBlue()).append("\n");
		sb.append("Position: X1 = ").append(x)
			.append(" | Y1 = ").append(y)
			.append(" | X2 = ").append(w)
			.append(" | Y2 = ").append(h).append("\n\n");
		return sb.toString();
	}
 
}
