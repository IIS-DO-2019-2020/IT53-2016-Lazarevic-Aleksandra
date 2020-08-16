package command.position;

import mvc.Model;
import command.Command;
import shapes.Shape;

public class BringToFront implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Shape s;
	private Model model;
	private int index;

	public BringToFront(Shape s, Model model) {
		this.s = s;
		this.model = model;
		this.index = model.getAllShapes().indexOf(s);
	}

	@Override
	public void execute() {
		model.removeShape(s);
		model.addShape(s);
	}

	@Override
	public void unexecute() {
		model.removeShape(s);
		model.getAllShapes().add(index, s);
	}

	@Override
	public String toString() {
		return "bringtofront:" + s.toString();
	}

}
