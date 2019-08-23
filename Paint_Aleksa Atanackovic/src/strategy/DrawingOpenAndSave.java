package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import mvc.DrawingFrame;
import mvc.DrawingModel;
import mvc.Shape;

public class DrawingOpenAndSave implements FileStrategy{

	@Override
	public void save(DrawingModel model, DrawingFrame frame, File file) {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file + ".pnt");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOutput);
			
			objectOut.writeObject(model.getAll());
			
			objectOut.close();
			fileOutput.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(file + ".txt"));
			
			for (int i = 0; i < frame.getListModel().getSize(); i++) {
				writer.write(frame.getListModel().getElementAt(i));
				writer.newLine();				
			}
			writer.close();
				
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void open(DrawingModel model, DrawingFrame frame, File file) {
		try {
			frame.getListModel().clear();

			FileInputStream fileInput = new FileInputStream(file);
			ObjectInputStream objectInput = new ObjectInputStream(fileInput);
			
	        model.setShapes((ArrayList<Shape>) objectInput.readObject());

	        objectInput.close();
	        fileInput.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		frame.getListModel().clear();
		
		String path = file.getPath();
		path = path.replace(".pnt", ".txt");
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line;
			
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				frame.getListModel().addElement(line);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		 
			
	}

}
