package command.shape;

import command.Command;
import shapes.Square;

public class EditSquare implements Command {
	
	private Square oldState;
	private Square newState;
	private Square originalState = new Square();

	public EditSquare(Square oldState, Square newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();

		oldState.setUpperLeft(newState.getUpperLeft());
		oldState.setSide(newState.getSide());
		oldState.setOutlineColor(newState.getOutlineColor());
		oldState.setInsideColor(newState.getInsideColor());
	}

	@Override
	public void unexecute() {
		oldState.setUpperLeft(originalState.getUpperLeft());
		oldState.setSide(originalState.getSide());
		oldState.setOutlineColor(originalState.getOutlineColor());
		oldState.setInsideColor(originalState.getInsideColor());
	}
	
	@Override
	public String toString() {
		return "edit:" + originalState.toString() + "to:" + newState;
	}

}
