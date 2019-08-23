package command;

import mvc.Point;

public class CmdUpdatePoint implements Command {
	private Point oldState;
	private Point newState;
	private Point originalState = new Point();

	public CmdUpdatePoint(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState.setX(oldState.getX());
		originalState.setY(oldState.getY());
		originalState.setColor(oldState.getColor());

		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setColor(newState.getColor());

	}

	@Override
	public void unexecute() {
		oldState.setX(originalState.getX());
		oldState.setY(originalState.getY());
		oldState.setColor(originalState.getColor());

	}

}
