package command;

import shapes.Circle;

public class EditCircle implements Command {
	
	private Circle oldState;
	private Circle newState;
	private Circle originalState = new Circle();

	public EditCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();

		oldState.setCenter(newState.getCenter());
		oldState.setRadius(newState.getRadius());
		oldState.setOutlineColor(newState.getOutlineColor());
		oldState.setInsideColor(newState.getInsideColor());
	}

	@Override
	public void unexecute() {
		oldState.setCenter(originalState.getCenter());
		oldState.setRadius(originalState.getRadius());
		oldState.setOutlineColor(originalState.getOutlineColor());
		oldState.setInsideColor(originalState.getInsideColor());
	}
	
	@Override
	public String toString() {
		return "edit:" + originalState.toString() + ",to:" + newState;
	}


}
