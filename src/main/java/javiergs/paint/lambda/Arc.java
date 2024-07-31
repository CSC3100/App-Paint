package javiergs.gui.paint.lambda;

import java.awt.*;
import java.io.*;

/**
 * The Arc class represents an arc shape.
 */
public class Arc extends Shape implements Serializable {
	
	private int startAngle;
	private boolean flip;
	
	public Arc(Color color, int x, int y, int w, int h, boolean flip) {
		super(color, x, y, w, h);
		this.flip = flip;
		startAngle = flip ? 180 : 0;
		
	}
	
	@Override
	public void drawShape(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(color);
		int adjustedY = flip ? y - h : y;
		int arcAngle = 180;
		g.fillArc(x, adjustedY, w, h * 2, startAngle, arcAngle);
		if (Officer.getInstance().getBaseShapeComponent() != null && Officer.getInstance().getBaseShapeComponent().equals(this)) {
			g2d.setColor(Color.MAGENTA);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawArc(x, adjustedY, w, h * 2, startAngle, arcAngle);
		}
	}
	
	@Serial
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(color.getRGB());
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(w);
		out.writeInt(h);
		out.writeInt(startAngle);
		out.writeBoolean(flip);
	}
	
	@Serial
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		color = new Color(in.readInt());
		x = in.readInt();
		y = in.readInt();
		w = in.readInt();
		h = in.readInt();
		startAngle = in.readInt();
		flip = in.readBoolean();
	}
	
	@Override
	public boolean contains(int x, int y) {
		return x >= this.x && x <= this.x + w && y >= this.y && y <= this.y + h;
	}
	
	@Override
	public Component clone() {
		return new Arc(this.color, this.x, this.y, this.w, this.h, this.flip);
	}
	
	public String toString() {
		return "Arc: x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + ", color=" + color;
	}
	
}
