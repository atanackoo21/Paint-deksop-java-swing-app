package command;

import mvc.DrawingModel;
import mvc.Shape;

public class CmdSelectShape implements Command{
	private Shape shape;
	private DrawingModel model;
	
	public CmdSelectShape(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
	}

	@Override
	public void execute() {
		shape.setSelected(true);
		model.addSelected(this.shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(false);
		model.removeSelected(this.shape);
		
	}

}
