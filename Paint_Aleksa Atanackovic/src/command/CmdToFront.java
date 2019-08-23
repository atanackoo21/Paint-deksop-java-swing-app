package command;

import mvc.DrawingModel;
import mvc.Shape;

public class CmdToFront implements Command{
	
	private Shape shape;
	private DrawingModel model;
	private int indexOfShape;
	
	public CmdToFront(Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
		this.indexOfShape = model.getIndexOfShape(this.shape);
	}
	
	@Override
	public void execute() {
		model.remove(shape);
		model.addOnIndex(shape, indexOfShape + 1);

	}

	@Override
	public void unexecute() {
		model.remove(shape);
		if (indexOfShape == 0)
			model.addOnIndex(shape, 0);
		else
			model.addOnIndex(shape, indexOfShape - 1);

	}

}
