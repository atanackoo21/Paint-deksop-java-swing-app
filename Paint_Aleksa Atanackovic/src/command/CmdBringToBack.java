package command;

import mvc.DrawingModel;
import mvc.Shape;

public class CmdBringToBack implements Command{

	private Shape shape;
	private DrawingModel model;
	private int indexOfShape;
	private int sizeList;
	
	public CmdBringToBack (Shape shape, DrawingModel model) {
		this.shape = shape;
		this.model = model;
		this.indexOfShape = model.getIndexOfShape(this.shape);
		this.sizeList = model.getAll().size();
	}

	@Override
	public void execute() {
		model.remove(shape);
		model.addOnIndex(shape, 0);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
		model.addOnIndex(shape, indexOfShape);
		
	}

}
