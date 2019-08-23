package command;

import mvc.Circle;
import mvc.Line;

public class CmdUpdateCircle implements Command {
	
	private Circle oldState;
	private Circle newState;
	private Circle originalState = new Circle();

	public CmdUpdateCircle(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState.setCenter(oldState.getCenter());
		originalState.setR(oldState.getR());
		originalState.setColor(oldState.getColor());
		originalState.setAreaColor(oldState.getAreaColor());

		oldState.setCenter(newState.getCenter());
		oldState.setR(newState.getR());
		oldState.setColor(newState.getColor());
		oldState.setAreaColor(newState.getAreaColor());


	}

	@Override
	public void unexecute() {
		oldState.setCenter(originalState.getCenter());
		oldState.setR(originalState.getR());
		oldState.setColor(originalState.getColor());
		oldState.setAreaColor(originalState.getAreaColor());
	}

}