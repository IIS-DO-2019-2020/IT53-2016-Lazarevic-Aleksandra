package command;

import shapes.Line;

public class EditLine implements Command {
	private Line oldState;
	private Line newState;
	private Line originalState = new Line();

	public EditLine(Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		
		oldState.setStart(newState.getStart());
		oldState.setEnd(newState.getEnd());
		oldState.setOutlineColor(newState.getOutlineColor());
		
	}

	@Override
	public void unexecute() {
		oldState.setStart(originalState.getStart());
		oldState.setEnd(originalState.getEnd());
		oldState.setOutlineColor(originalState.getOutlineColor());
	}
	
	@Override
	public String toString() {
		return "edit:" + originalState.toString() + ",to:" + newState;
	}

}