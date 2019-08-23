package command;

import mvc.Rectangle;

public class CmdUpdateRectangle implements Command{
	
	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle originalState = new Rectangle();

	public CmdUpdateRectangle(Rectangle oldState, Rectangle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState.setUpLeft(oldState.getUpLeft());
		originalState.setWidth(oldState.getWidth());
		originalState.setLength(oldState.getLength());
		originalState.setColor(oldState.getColor());
		originalState.setAreaColor(oldState.getAreaColor());

		oldState.setUpLeft(newState.getUpLeft());
		oldState.setWidth(newState.getWidth());
		oldState.setLength(newState.getLength());
		oldState.setColor(newState.getColor());
		oldState.setAreaColor(newState.getAreaColor());

	}

	@Override
	public void unexecute() {
		oldState.setUpLeft(originalState.getUpLeft());
		oldState.setWidth(originalState.getWidth());
		oldState.setLength(originalState.getLength());
		oldState.setColor(originalState.getColor());
		oldState.setAreaColor(originalState.getAreaColor());
	}

}