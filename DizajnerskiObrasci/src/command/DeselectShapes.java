package command;

import java.util.ArrayList;

import mvc.Frame;
import mvc.Model;
import shapes.Shape;



public class DeselectShapes implements Command {
	private Model model;
	private Frame frame;
	private ArrayList<Shape> selectedShapes;

	public DeselectShapes(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
		selectedShapes = new ArrayList<Shape>();

		for (Shape s : model.getAllShapes()) {
			if (s.isSelected()) {
				selectedShapes.add(s);
			}
		}
	}
	@Override
	public void execute() {
		for (Shape s : model.getAllShapes()) {
			s.setObserver(frame);
			s.setSelected(false);
		}
	}

	@Override
	public void unexecute() {
		for(Shape s : selectedShapes) {
			s.setSelected(true);
		}
	}

	@Override
	public String toString() {
		return "deselectAll";
	}

}
