package command;

import mvc.DrawingModel;
import mvc.Shape;

public class CmdBringToFront implements Command{
	
	private Shape shape;
	private DrawingModel model;
	private int indexOfShape;
	
	public CmdBringToFront (Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
		this.indexOfShape = model.getIndexOfShape(this.shape);
	}

	@Override
	public void execute() {
		model.remove(shape);
		model.add(shape);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.addOnIndex(shape, indexOfShape);
		
	}

}
