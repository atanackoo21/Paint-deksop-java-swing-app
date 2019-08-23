package strategy;

import java.io.File;

import mvc.DrawingFrame;
import mvc.DrawingModel;

public interface FileStrategy {
	void save(DrawingModel model, DrawingFrame frame, File file);
	void open(DrawingModel model, DrawingFrame frame, File file);
}
