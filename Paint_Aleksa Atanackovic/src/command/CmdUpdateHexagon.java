package command;

import mvc.HexagonAdapter;

public class CmdUpdateHexagon implements Command{

	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter originalState = new HexagonAdapter();

	public CmdUpdateHexagon(HexagonAdapter oldState, HexagonAdapter newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState.setX(oldState.getX());
		originalState.setY(oldState.getY());
		originalState.setR(oldState.getR());
		originalState.setColor(oldState.getColor());
		originalState.setAreaColor(oldState.getAreaColor());

		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setR(newState.getR());
		oldState.setColor(newState.getColor());
		oldState.setAreaColor(newState.getAreaColor());

	}

	@Override
	public void unexecute() {
		oldState.setX(originalState.getX());
		oldState.setY(originalState.getY());
		oldState.setR(originalState.getR());
		oldState.setColor(originalState.getColor());
		oldState.setAreaColor(originalState.getAreaColor());
	}

}
