package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import shapes.Shape;

public class Model implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> listOfShapes = new ArrayList<Shape>();

	public Model() {
	}

	public void addShape(Shape s) {
		listOfShapes.add(s); 
	}

	public void removeShape(Shape s) {
		listOfShapes.remove(s);
	}

	public Shape getShape(int index) {
		return listOfShapes.get(index);
	}

	public ArrayList<Shape> getAllShapes() {
		return listOfShapes;
	}
}