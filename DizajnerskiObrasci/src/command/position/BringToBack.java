package command.position;

import command.Command;
import mvc.Model;
import shapes.Shape;

public class BringToBack implements Command {
	
	private Shape s;
	private Model model;
	private int shapeIndex;

	public BringToBack(Shape s, Model model) {
		this.s = s;
		this.model = model;
		this.shapeIndex = model.getAllShapes().indexOf(s);
	}

	@Override
	public void execute() {
		model.removeShape(s);
		model.getAllShapes().add(0, s);
	}

	@Override
	public void unexecute() {
		model.removeShape(s);
		model.getAllShapes().add(shapeIndex, s);
	}

	@Override
	public String toString() {
		return "bringtoback:" + s.toString();
	}

}
