package shapes;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;


public class HexagonAdapter extends InsideShape implements Movable{

	private Hexagon hexagon;

	public HexagonAdapter(Hexagon h) {
		this.hexagon = h;
	}

	public int getX() {
		return this.hexagon.getX();
	}

	public int getY() {
		return this.hexagon.getY();
	}

	public int getR() {
		return this.hexagon.getR();
	}

	public void setX(int x) {
		this.hexagon.setX(x);
	}

	public void setY(int y) {
		this.hexagon.setY(y);
	}

	public void setR(int r) {
		this.hexagon.setR(r);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			HexagonAdapter forwarded = (HexagonAdapter) o;
			return this.getR() - forwarded.getR();
		} else {
			return 0;
		}
	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
	}

	@Override
	public boolean contains(int x, int y) {
		return this.hexagon.doesContain(x, y);
	}
	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
		
	}

	@Override
	public void moveFor(int x, int y) {
		hexagon.setX(hexagon.getX() + x);
		hexagon.setY(hexagon.getY() + y);
		
	}
	@Override
	public HexagonAdapter clone() {
		Hexagon hex = new Hexagon(this.hexagon.getX(), this.getY(), this.getR());
		HexagonAdapter hexagonClone = new HexagonAdapter(hex);
		hexagonClone.setInsideColor(this.getInsideColor());
		hexagonClone.setOutlineColor(this.getOutlineColor());
		return hexagonClone;
	}

	@Override
	public void fill(Graphics g) {
		this.hexagon.paint(g);
	}

	@Override
	public void selected(Graphics g) {
		this.hexagon.paint(g);
	}

	@Override
	public void setOutlineColor(Color outlineColor) {
		this.hexagon.setBorderColor(outlineColor);
	}

	@Override
	public void setInsideColor(Color insideColor) {
		this.hexagon.setAreaColor(insideColor);
	}

	@Override
	public void setSelected(boolean b) {
		this.hexagon.setSelected(b);
		super.notifyObserver();
	}

	@Override
	public Color getOutlineColor() {
		return this.hexagon.getBorderColor();
	}

	@Override
	public Color getInsideColor() {
		return this.hexagon.getAreaColor();
	}

	@Override
	public boolean isSelected() {
		return this.hexagon.isSelected();
	}

	@Override
	public String toString() {
		Point center = new Point(hexagon.getX(), hexagon.getY());
		return "hexagon:center" + center.getCoordinatesText() + "radius(" + hexagon.getR() + ")" + this.getOutlineText()+ this.getInsideText();
	}

	@Override
	public String getOutlineText() {
		return "outline(" + this.hexagon.getBorderColor().getRed() + "." + this.hexagon.getBorderColor().getGreen() + "."
				+ this.hexagon.getBorderColor().getBlue() + ")";
	}

	@Override
	public String getInsideText() {
		return "inside(" + this.hexagon.getAreaColor().getRed() + "." + this.hexagon.getAreaColor().getGreen() + "."
				+ this.hexagon.getAreaColor().getBlue() + ")";
	}

	@Override
	public boolean equals(Object second) {
		if (second instanceof HexagonAdapter) {
			HexagonAdapter secondHexagon = (HexagonAdapter) second;
			if (this.getX() == secondHexagon.getX() && this.getY() == secondHexagon.getY()
					&& this.getR() == secondHexagon.getR())
				return true;
			else {
				return false;
			}
		} else {
			return false;
		}
	}
}
