package observerPaint;

import mvc.DrawingFrame;
import mvc.DrawingModel;

public class DrawObserver implements Observer{
	private DrawingFrame frame;
	private DrawingModel model;
	
	public DrawObserver(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void update(int numOfSelected, int numOfElements, int  numOfUndoCommands, int numOfRedoCommands) {
		if (numOfSelected > 0) {
			frame.getBtnDelete().setEnabled(true);
		} else {
			frame.getBtnDelete().setEnabled(false);
		}
		
		if (numOfSelected == 1) {
			frame.getBtnModify().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(true);
			frame.getBtnBringToFront().setEnabled(true);
			frame.getBtnToBack().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
		} else {
			frame.getBtnModify().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
		}
		
		
		if (numOfUndoCommands > 0) {
			frame.getBtnUndo().setEnabled(true);
		}else {
			frame.getBtnUndo().setEnabled(false);
		}
		
		if (numOfRedoCommands > 0) {
			frame.getBtnRedo().setEnabled(true);
		}else {
			frame.getBtnRedo().setEnabled(false);
		}
	}
	
}
