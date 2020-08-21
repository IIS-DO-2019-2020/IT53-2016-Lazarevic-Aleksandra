package command.shape;

import command.Command;
import shapes.Rectangle;

public class EditRectangle implements Command {

	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState = new Rectangle();

	public EditRectangle(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();

		oldState.setUpperLeft(newState.getUpperLeft());
		oldState.setHeight(newState.getHeight());
		oldState.setSide(newState.getSide());
		oldState.setOutlineColor(newState.getOutlineColor());
		oldState.setInsideColor(newState.getInsideColor());
	}

	@Override
	public void unexecute() {
		oldState.setUpperLeft(originalState.getUpperLeft());
		oldState.setHeight(originalState.getHeight());
		oldState.setSide(originalState.getSide());
		oldState.setOutlineColor(originalState.getOutlineColor());
		oldState.setInsideColor(originalState.getInsideColor());
	}
	
	@Override
	public String toString() {
		return "edit:" + originalState.toString() + "to:" + newState;
	}

}
