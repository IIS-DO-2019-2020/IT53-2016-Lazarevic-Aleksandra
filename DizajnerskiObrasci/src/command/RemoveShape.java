package command;

import mvc.Model;
import shapes.Shape;

public class RemoveShape implements Command {

	private Shape s;
	private Model model;
	
	public RemoveShape(Model model,Shape s) {
		this.model=model;
		this.s = s;
	}

	@Override
	public void execute() {
		model.getAllShapes().remove(s);

	}

	@Override
	public void unexecute() {
		model.getAllShapes().add(s);

	}
	@Override
	public String toString() {
		return "remove:" + s.toString();
	}


}