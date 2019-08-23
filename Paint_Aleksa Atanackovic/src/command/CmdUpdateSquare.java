package command;

import mvc.Square;

public class CmdUpdateSquare implements Command{
	
	private Square oldState;
	private Square newState;
	private Square originalState = new Square();

	public CmdUpdateSquare(Square oldState, Square newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState.setUpLeft(oldState.getUpLeft());
		originalState.setLength(oldState.getLength());
		originalState.setColor(oldState.getColor());
		originalState.setAreaColor(oldState.getAreaColor());

		oldState.setUpLeft(newState.getUpLeft());
		oldState.setLength(newState.getLength());
		oldState.setColor(newState.getColor());
		oldState.setAreaColor(newState.getAreaColor());

	}

	@Override
	public void unexecute() {
		oldState.setUpLeft(originalState.getUpLeft());
		oldState.setLength(originalState.getLength());
		oldState.setColor(originalState.getColor());
		oldState.setAreaColor(originalState.getAreaColor());
	}

}