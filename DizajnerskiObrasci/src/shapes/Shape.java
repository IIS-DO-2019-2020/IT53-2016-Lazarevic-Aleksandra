package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import observer.Observer;
import observer.Subject;


public abstract class Shape implements Comparable, Subject, Serializable {

	
	protected boolean selected;
	private Color outlineColor;
	private Observer observer;

	public Shape() {

	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color color) {
		this.outlineColor = color;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		notifyObserver();
	}

	public void notifyObserver() {
		this.observer.update();
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	public String getOutlineText() {
		return "outline(" + outlineColor.getRed() + "." + outlineColor.getGreen() + "." + outlineColor.getBlue() + ")";
	}

	public abstract void draw(Graphics g);

	public abstract void selected(Graphics g);

	public abstract boolean contains(int x, int y);

	public abstract Shape clone();
}