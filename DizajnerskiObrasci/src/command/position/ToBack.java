package command.position;

import java.util.Collections;

import command.Command;
import mvc.Model;
import shapes.Shape;

public class ToBack implements Command {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Shape s;
	private Model model;
	private int index;

	public ToBack(Shape s, Model model) {
		this.s = s;
		this.model = model;
	}

	@Override
	public void execute() {
		index = model.getAllShapes().indexOf(s);
		Collections.swap(model.getAllShapes(), index, index - 1);
	}

	@Override
	public void unexecute() {
		this.index = model.getAllShapes().indexOf(s);
		Collections.swap(model.getAllShapes(), index, index + 1);
	}

	public String toString() {
		return "toback:" + s.toString();
	}

}
