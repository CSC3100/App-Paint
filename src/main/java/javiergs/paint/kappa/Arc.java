package javiergs.gui.paint.kappa;

import java.awt.*;
import java.io.*;

/**
 * The Arc class represents a drawable arc shape.
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
		g.setColor(color);
		int adjustedY = flip ? y - h : y;
		int arcAngle = 180;
		g.fillArc(x, adjustedY, w, h * 2, startAngle, arcAngle);
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
		int adjustedY = flip ? this.y - this.h : this.y;
		return x >= this.x && x <= this.x + w && y >= adjustedY && y <= adjustedY + h * 2;
	}
	
	@Override
	public Shape clone() {
		return new Arc(this.color, this.x, this.y, this.w, this.h, this.flip);
	}
	
	@Override
	public void drawSelectionHighlight(Graphics g) {
		super.drawSelectionHighlight(g);
		Graphics2D g2d = (Graphics2D) g;
		int adjustedY = flip ? y - h : y;
		g2d.drawArc(x, adjustedY, w, h * 2, startAngle, 180);
		int centerY = adjustedY + h;
		g2d.drawLine(x, centerY, x + w, centerY);
	}
	
}