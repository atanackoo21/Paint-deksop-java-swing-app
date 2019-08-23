package command;

import mvc.Line;
import mvc.Point;

public class CmdUpdateLine implements Command {
	
	private Line oldState;
	private Line newState;
	private Line originalState = new Line();
	
	public CmdUpdateLine(Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState.setStartPoint(oldState.getStartPoint());
		originalState.setEndPoint(oldState.getEndPoint());
		originalState.setColor(oldState.getColor());

		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setColor(newState.getColor());

	}

	@Override
	public void unexecute() {
		oldState.setStartPoint(originalState.getStartPoint());
		oldState.setEndPoint(originalState.getEndPoint());
		oldState.setColor(originalState.getColor());
	}

}