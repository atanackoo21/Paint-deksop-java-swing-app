package strategy;

import java.io.File;

import mvc.DrawingFrame;
import mvc.DrawingModel;

public class FileManager implements FileStrategy{
	
	private FileStrategy fileStrategy;

	public FileManager(FileStrategy fileStrategy) {
		super();
		this.fileStrategy = fileStrategy;
	}

	@Override
	public void save(DrawingModel model, DrawingFrame frame, File file) {
		fileStrategy.save(model, frame, file);
		
	}

	@Override
	public void open(DrawingModel model, DrawingFrame frame, File file) {
		fileStrategy.open(model, frame, file);

	}



}
