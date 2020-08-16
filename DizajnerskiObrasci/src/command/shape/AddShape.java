package command.shape;

import command.Command;
import mvc.Model;
import shapes.Shape;

public class AddShape implements Command{

	private static final long serialVersionUID = 1L;
	private Model model;
	private Shape shape;
	
	public AddShape(Model model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		model.addShape(shape);
	}

	@Override
	public void unexecute() {
		model.removeShape(shape);
	}
	
	@Override
	public String toString() {
		return "add:" + shape.toString();
	}
	
}