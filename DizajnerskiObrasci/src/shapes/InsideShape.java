package shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class InsideShape extends Shape {

	private Color insideColor;

	public Color insideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}

	public Color getInsideColor() {
		return insideColor;
	}

	public String getInsideText() {
		return "inside(" + insideColor.getRed() + "," + insideColor.getGreen() + "," + insideColor.getBlue() + ")";
	}

	public abstract void fill(Graphics g);

}