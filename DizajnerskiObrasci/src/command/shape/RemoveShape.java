package command.shape;

import command.Command;
import mvc.Model;
import shapes.Shape;

public class RemoveShape implements Command {

	private Shape s;
	private Model model;
	private int index;
	
	public RemoveShape(Model model,Shape s) {
		this.model=model;
		this.s = s;
	}

	@Override
	public void execute() {
		index=model.getAllShapes().indexOf(s);
		model.getAllShapes().remove(s);

	}

	@Override
	public void unexecute() {
		model.getAllShapes().add(index,s);

	}
	@Override
	public String toString() {
		return "remove:" + s.toString();
	}


}