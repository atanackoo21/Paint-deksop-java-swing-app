package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class LogOpenAndSave implements FileStrategy{
	
	public LogOpenAndSave() {
		
	}

	@Override
	public void save(DrawingModel model, DrawingFrame frame, File file) {
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(file + ".txt"));
			
			for (int i = 0; i < frame.getListModel().getSize(); i++) {
				writer.write(frame.getListModel().getElementAt(i));
				writer.newLine();				
			}
			writer.close();
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void open(DrawingModel model, DrawingFrame frame, File file) {
		
		frame.getListModel().clear();
		model.getAll().clear();

		try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {

			String line;
			
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				frame.getListModel().addElement(line);
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
