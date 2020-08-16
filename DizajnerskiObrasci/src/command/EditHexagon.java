package command;

import shapes.HexagonAdapter;

public class EditHexagon implements Command {
	
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState = new HexagonAdapter(null);

	public EditHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();

		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setR(newState.getR());
		oldState.setOutlineColor(newState.getOutlineColor());
		oldState.setInsideColor(newState.getInsideColor());
	}

	@Override
	public void unexecute() {
		oldState.setX(originalState.getX());
		oldState.setY(originalState.getY());
		oldState.setR(originalState.getR());
		oldState.setOutlineColor(originalState.getOutlineColor());
		oldState.setInsideColor(originalState.getInsideColor());
	}
	
	@Override
	public String toString() {
		return "edit:" + originalState.toString() + ",to:" + newState;
	}
}
