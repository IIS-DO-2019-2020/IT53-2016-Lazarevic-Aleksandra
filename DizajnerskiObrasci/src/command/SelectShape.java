package command;

import shapes.Shape;

public class SelectShape implements Command {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Shape s;
	
	public SelectShape(Shape s) {
		this.s = s;
	}

	@Override
	public void execute() {
		s.setSelected(true);

	}

	@Override
	public void unexecute() {
		s.setSelected(false);

	}
	@Override
	public String toString() {
		return "select:" + s.toString();
	}


}
