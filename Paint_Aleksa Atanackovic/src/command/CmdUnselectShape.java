package command;

import mvc.DrawingModel;
import mvc.Shape;

public class CmdUnselectShape implements Command{
	private Shape shape;
	private DrawingModel model;
	
	
	public CmdUnselectShape(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		this.shape.setSelected(false);
		model.removeSelected(this.shape);

	}

	@Override
	public void unexecute() {
		this.shape.setSelected(true);
		model.addSelected(this.shape);
	}

}
