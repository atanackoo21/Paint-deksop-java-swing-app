package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import mvc.Circle;
import observerPaint.DrawObserver;
import strategy.DrawingOpenAndSave;
import strategy.FileManager;
import strategy.LogOpenAndSave;
import command.CmdAddShape;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdRemoveShape;
import command.CmdSelectShape;
import command.CmdToBack;
import command.CmdToFront;
import command.CmdUnselectShape;
import command.CmdUpdateCircle;
import command.CmdUpdateHexagon;
import command.CmdUpdateLine;
import command.CmdUpdatePoint;
import command.CmdUpdateRectangle;
import command.CmdUpdateSquare;
import dialogs.DlgAddCircle;
import dialogs.DlgAddHexagon;
import dialogs.DlgAddRectangle;
import dialogs.DlgAddSquare;
import dialogs.DlgModifyCircle;
import dialogs.DlgModifyHexagon;
import dialogs.DlgModifyLine;
import dialogs.DlgModifyPoint;
import dialogs.DlgModifyRectangle;
import dialogs.DlgModifySquare;

public class DrawingController {
	private DrawingModel model;
	private DrawingFrame frame;
	private int clickCounter;
	private Point firstPoint;
	private int selectedIndex = -1;
	private DrawObserver drawObserver;
	private int currentDrawLog = 0;
	private DefaultListModel<String> listModelTmp = new DefaultListModel<String>();

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		drawObserver = new DrawObserver (frame);
		model.addObserver(drawObserver);
	}

	public void mouseClicked(MouseEvent arg0) {
		
		if (model.getAllSelected().size() > 0) {
			while (model.getAllSelected().size()>0) {
			
			frame.getListModel().addElement("Unselect:" + model.getAllSelected().get(0).toString());

			CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(model.getAllSelected().get(0), model);
			cmdUnselectShape.execute();
			
			model.addUndoCommands(cmdUnselectShape);
			
			}
		}
		if (frame.getBtnDrawLog().isEnabled()) {
			logEnd();
		}
		if (frame.getTglbtnSelect().isSelected()) {
			select (new Point(arg0.getX(), arg0.getY()));
		
		} else if (frame.getTglbtnPoint().isSelected()) {
			
			Point point = new Point (arg0.getX(), arg0.getY());
			point.setColor(frame.getBtnBorderColor().getBackground());
			CmdAddShape cmdAddShape = new CmdAddShape(point, model);
			cmdAddShape.execute();
			
			model.addUndoCommands(cmdAddShape);
			
			frame.getListModel().addElement("Draw:" + point.toString());
		
		} else if(frame.getTglbtnLine().isSelected()){
			if (clickCounter == 0) {
				firstPoint = new Point (arg0.getX(), arg0.getY());
				clickCounter++;
			} else if (clickCounter == 1) {
				Line line = new Line(firstPoint, new Point (arg0.getX(), arg0.getY()));
				line.setColor(frame.getBtnBorderColor().getBackground());
				CmdAddShape cmdAddShape = new CmdAddShape(line, model);
				cmdAddShape.execute();
				clickCounter = 0;
				
				model.addUndoCommands(cmdAddShape);
				
				frame.getListModel().addElement("Draw:" + line.toString());
			}
			
		}
		else if(frame.getTglbtnCircle().isSelected()) {
			DlgAddCircle dlgCircle = new DlgAddCircle();
			dlgCircle.setVisible(true);
			int r = dlgCircle.getRadius();

			Circle circle = new Circle(new Point(arg0.getX(), arg0.getY()), r);
			
			circle.setColor(frame.getBtnBorderColor().getBackground());
			circle.setAreaColor(frame.getBtnAreaColor().getBackground());
			
			if (dlgCircle.getRadius() > 0) {
				CmdAddShape cmdAddShape = new CmdAddShape(circle, model); 
				cmdAddShape.execute();
				
				model.addUndoCommands(cmdAddShape);
				
				frame.getListModel().addElement("Draw:" + circle.toString());
			}

		} else if (frame.getTglbtnSquare().isSelected()) {
			DlgAddSquare dlgAddSquare = new DlgAddSquare();

			dlgAddSquare.setVisible(true);
			
			Square square = new Square (new Point(arg0.getX(), arg0.getY()), dlgAddSquare.getWidthSquare());
			square.setColor(frame.getBtnBorderColor().getBackground());
			square.setAreaColor(frame.getBtnAreaColor().getBackground());
			
			if(dlgAddSquare.getWidthSquare() > 0) {
			
				CmdAddShape cmdAddShape = new CmdAddShape(square, model);
				cmdAddShape.execute();
				
				model.addUndoCommands(cmdAddShape);
				
				frame.getListModel().addElement("Draw:" + square.toString());
			}
		} else if (frame.getTglbtnRectangle().isSelected()) {
			DlgAddRectangle dlgRectangle = new DlgAddRectangle();

			dlgRectangle.setVisible(true);

			Rectangle rectangle = new Rectangle (new Point(arg0.getX(), arg0.getY()), dlgRectangle.getRecHeight(), dlgRectangle.getRecWidth());
			rectangle.setColor(frame.getBtnBorderColor().getBackground());
			rectangle.setAreaColor(frame.getBtnAreaColor().getBackground());
			
			if (dlgRectangle.getRecHeight() > 0 || dlgRectangle.getRecWidth() > 0) {
				CmdAddShape cmdAddShape = new CmdAddShape(rectangle, model);
				cmdAddShape.execute();
				
				model.addUndoCommands(cmdAddShape);
				
				frame.getListModel().addElement("Draw:" + rectangle.toString());
			}
		} else if (frame.getTglbtnHexagon().isSelected()) {
			DlgAddHexagon dlgHexagon = new DlgAddHexagon();

			dlgHexagon.setVisible(true);

			int r = dlgHexagon.getLengthHex();
			
			if (r > 0) {
			HexagonAdapter hexagon = new HexagonAdapter (arg0.getX(), arg0.getY(), r); 
			hexagon.setColor(frame.getBtnBorderColor().getBackground());
			hexagon.setAreaColor(frame.getBtnAreaColor().getBackground());
			
			CmdAddShape cmdAddShape = new CmdAddShape(hexagon, model);
			cmdAddShape.execute();
			
			model.addUndoCommands(cmdAddShape);
			
			frame.getListModel().addElement("Draw:" + hexagon.toString());

			}
			
		} 

		frame.getView().repaint();

	}
	
	public void select(Point click) {

		if (frame.getBtnDrawLog().isEnabled()) {
			logEnd();
		}
		ArrayList<Shape> elements = new ArrayList<Shape>();
		elements = model.getAll();
		ArrayList <Shape> selecElem = model.getAllSelected();

		selectedIndex = -1;
		for (int i = elements.size() - 1; i >= 0; i--) {
			if (elements.get(i).contains(click.getX(), click.getY())) {

				if (elements.get(i).isSelected() == false){
					CmdSelectShape cmdSelectShape = new CmdSelectShape(elements.get(i), model);
					cmdSelectShape.execute();
					model.addUndoCommands(cmdSelectShape);
					
					frame.getListModel().addElement("Select:" + elements.get(i).toString());
				} else {
					CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(elements.get(i), model);
					cmdUnselectShape.execute();
					
					frame.getListModel().addElement("Unselect:" + elements.get(i).toString());
					
					model.addUndoCommands(cmdUnselectShape);

				}

				selectedIndex = i*i;
				
				break;
			}
		}
		
			if (!selecElem.isEmpty() && selectedIndex == -1) {

			while (selecElem.size()>0) {
				
				frame.getListModel().addElement("Unselect:" + selecElem.get(0).toString());

				CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(selecElem.get(0), model);
				cmdUnselectShape.execute();
				
				model.addUndoCommands(cmdUnselectShape);
				
			}
		}
		
		frame.repaint();

	}
	
	public void delete() {
		ArrayList<Shape> elements = model.getAllSelected();
		
		//u slucaju da se izbrise vise od jednog oblika potrebno je to upamtiti
		while (elements.size()>0) {
			CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(elements.get(0), model);
			cmdRemoveShape.execute();
			frame.getListModel().addElement("Delete:" + elements.get(0).toString());

			frame.getListModel().addElement("Unselect:" + elements.get(0).toString());
			
			CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(elements.get(0), model);
			cmdUnselectShape.execute();

			model.addUndoCommands(cmdRemoveShape);
			model.addUndoCommands(cmdUnselectShape);


		}
			
		frame.repaint();
	}
	
	public void modify() throws CloneNotSupportedException {

		Shape selectedShape = model.getSelected(0);
		
		if (selectedShape instanceof Point) {
			
			DlgModifyPoint dlgPoint = new DlgModifyPoint();
			Point point = (Point) selectedShape;
			
			dlgPoint.setTmpX(point.getX());
			dlgPoint.setTmpY(point.getY());
			dlgPoint.setColor(point.getColor());
			dlgPoint.setCheck(false);
			dlgPoint.getTxtX().setText(Integer.toString(point.getX()));
			dlgPoint.getTxtY().setText(Integer.toString(point.getY()));
			dlgPoint.getBtnColor().setBackground(point.getColor());
			
			dlgPoint.setVisible(true);
			
			Point clonePoint = (Point) point.clone();
			//clonePoint = new Point(dlgPoint.getTmpX(), dlgPoint.getTmpY(), dlgPoint.getColor());
			
			clonePoint.setX(dlgPoint.getTmpX());
			clonePoint.setY(dlgPoint.getTmpY());
			clonePoint.setColor(dlgPoint.getColor());
			
			if (dlgPoint.isCheck()) {
			
				frame.getListModel().addElement("Update:" + point.toString() + ", to:" + clonePoint.toString());
	
				CmdUpdatePoint cmdPoint = new CmdUpdatePoint (point, clonePoint); 
				cmdPoint.execute();
				
				frame.getListModel().addElement("Unselect:" + point.toString());
				
				CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(point, model);
				cmdUnselectShape.execute();
				
				model.addUndoCommands(cmdUnselectShape);
				model.addUndoCommands(cmdPoint);
			}
			
		} else if (selectedShape instanceof Line){
			DlgModifyLine dlgLine = new DlgModifyLine();
			Line line = (Line) selectedShape;
			
			dlgLine.setTmpStartX(line.getStartPoint().getX());
			dlgLine.setTmpStartY(line.getStartPoint().getY());
			dlgLine.setTmpEndX(line.getEndPoint().getX());
			dlgLine.setTmpEndY(line.getEndPoint().getY());
			dlgLine.setColor(line.getColor());
			dlgLine.setCheck(false);
			
			dlgLine.getTxtStartPointX().setText(Integer.toString(line.getStartPoint().getX()));
			dlgLine.getTxtStartPointY().setText(Integer.toString(line.getStartPoint().getY()));
			dlgLine.getTxtEndPointX().setText(Integer.toString(line.getEndPoint().getX()));
			dlgLine.getTxtEndPointY().setText(Integer.toString(line.getEndPoint().getY()));
			
			dlgLine.getBtnColor().setBackground(line.getColor());
			
			dlgLine.setVisible(true);
			
			try {
			Line line2 = (Line) line.clone();
			
			line2 = new Line(new Point(dlgLine.getTmpStartX(), dlgLine.getTmpStartY()), 
								  new Point (dlgLine.getTmpEndX(), dlgLine.getTmpEndY()),
								  dlgLine.getBtnColor().getBackground());
			if (dlgLine.isCheck()) {
				frame.getListModel().addElement("Update:" + line.toString() + ", to:" + line2.toString());
				
				CmdUpdateLine cmdLine = new CmdUpdateLine (line, line2); 
				cmdLine.execute();
				
				frame.getListModel().addElement("Unselect:" + line.toString());

				CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(line, model);
				cmdUnselectShape.execute();
				
				model.addUndoCommands(cmdUnselectShape);
				model.addUndoCommands(cmdLine);
			}

			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		} else if (selectedShape instanceof Circle){
			DlgModifyCircle dlgCircle = new DlgModifyCircle();
			Circle circle = (Circle) selectedShape;
			
			dlgCircle.setTmpCenterX(circle.getCenter().getX());
			dlgCircle.setTmpCenterY(circle.getCenter().getY());
			dlgCircle.setRadius(circle.getR());
			dlgCircle.setBorderColor(circle.getColor());
			dlgCircle.setAreaColor(circle.getAreaColor());
			dlgCircle.setCheck(false);

			dlgCircle.getTxtX().setText(Integer.toString(circle.getCenter().getX()));
			dlgCircle.getTxtY().setText(Integer.toString(circle.getCenter().getY()));
			dlgCircle.getTxtRadius().setText(Integer.toString(circle.getR()));
			dlgCircle.getBtnAreaColor().setBackground(circle.getAreaColor());
			dlgCircle.getBtnBorderColor().setBackground(circle.getColor());
			
			dlgCircle.setVisible(true);
			
			try {
			
			Circle circle2 = (Circle) selectedShape.clone();
			
			circle2 = new Circle(
					new Point (dlgCircle.getTmpCenterX(), dlgCircle.getTmpCenterY()),
					dlgCircle.getRadius(),
					dlgCircle.getBorderColor(),
					dlgCircle.getAreaColor());
			if (dlgCircle.isCheck()) {

				frame.getListModel().addElement("Update:" + circle.toString() + ", to:" + circle2.toString());
	
				
				CmdUpdateCircle cmdCircle = new CmdUpdateCircle (circle, circle2); 
				cmdCircle.execute();
				
				frame.getListModel().addElement("Unselect:" + circle.toString());
				
				CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(circle, model);
				cmdUnselectShape.execute();
				
				model.addUndoCommands(cmdUnselectShape);
				model.addUndoCommands(cmdCircle);
				
			} 
			}
			catch(CloneNotSupportedException e) {
					e.printStackTrace();
			}
			
		}  else if (selectedShape instanceof Rectangle){
			DlgModifyRectangle dlgRectangle = new DlgModifyRectangle();
			Rectangle rectangle = (Rectangle) selectedShape;
			
			dlgRectangle.setTmpX(rectangle.getUpLeft().getX());
			dlgRectangle.setTmpY(rectangle.getUpLeft().getY());
			dlgRectangle.setWidthRec(rectangle.getWidth());
			dlgRectangle.setLengthRec(rectangle.getLength());
			dlgRectangle.setBorderColor(rectangle.getColor());
			dlgRectangle.setAreaColor(rectangle.getAreaColor());
			dlgRectangle.setCheck(false);

			dlgRectangle.getTxtX().setText(Integer.toString(rectangle.getUpLeft().getX()));
			dlgRectangle.getTxtY().setText(Integer.toString(rectangle.getUpLeft().getY()));
			dlgRectangle.getTxtWidth().setText(Integer.toString(rectangle.getWidth()));
			dlgRectangle.getTxtLength().setText(Integer.toString(rectangle.getLength()));
			dlgRectangle.getBtnAreaColor().setBackground(rectangle.getAreaColor());
			dlgRectangle.getBtnBorderColor().setBackground(rectangle.getColor());
			
			dlgRectangle.setVisible(true);
			
			try {
				
				Rectangle rectangle2 = (Rectangle) selectedShape.clone();
				
				rectangle2 = new Rectangle(new Point (dlgRectangle.getTmpX(), dlgRectangle.getTmpY()),
						dlgRectangle.getLengthRec(),
						dlgRectangle.getWidthRec(),
						dlgRectangle.getBorderColor(),
						dlgRectangle.getAreaColor());
				if (dlgRectangle.isCheck()) {
					frame.getListModel().addElement("Update:" + rectangle.toString() + ", to:" + rectangle2.toString());
		
					
					CmdUpdateRectangle cmdRectangle = new CmdUpdateRectangle (rectangle, rectangle2); 
					cmdRectangle.execute();
					
					frame.getListModel().addElement("Unselect:" + rectangle.toString());

					CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(rectangle, model);
					cmdUnselectShape.execute();	
					
					model.addUndoCommands(cmdUnselectShape);
					model.addUndoCommands(cmdRectangle);
				}
			} catch(CloneNotSupportedException e) {
				e.printStackTrace();
			}
		} else if (selectedShape instanceof Square){
			DlgModifySquare dlgSquare = new DlgModifySquare();
			Square square = (Square) selectedShape;
			
			dlgSquare.setTmpX(square.getUpLeft().getX());
			dlgSquare.setTmpY(square.getUpLeft().getY());
			dlgSquare.setTmpWidth(square.getLength());
			dlgSquare.setBorderColor(square.getColor());
			dlgSquare.setAreaColor(square.getAreaColor());
			dlgSquare.setCheck(false);

			dlgSquare.getTxtX().setText(Integer.toString(square.getUpLeft().getX()));
			dlgSquare.getTxtY().setText(Integer.toString(square.getUpLeft().getY()));
			dlgSquare.getTxtWidth().setText(Integer.toString(square.getLength()));
			dlgSquare.getBtnAreaColor().setBackground(square.getAreaColor());
			dlgSquare.getBtnBorderColor().setBackground(square.getColor());
			
			dlgSquare.setVisible(true);
			
			try {
			
			Square square2 = (Square) selectedShape.clone();
			
			square2 = new Square(
					new Point (dlgSquare.getTmpX(), dlgSquare.getTmpY()),
					dlgSquare.getTmpWidth(),
					dlgSquare.getBorderColor(),
					dlgSquare.getAreaColor());
				if (dlgSquare.isCheck()) {
					frame.getListModel().addElement("Update:" + square.toString() + ", to:" + square2.toString());
					
					CmdUpdateSquare cmdSquare = new CmdUpdateSquare (square, square2); 
					cmdSquare.execute();
					
					frame.getListModel().addElement("Unselect:" + square.toString());
					
					CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(square, model);
					cmdUnselectShape.execute();
					
					model.addUndoCommands(cmdUnselectShape);
					model.addUndoCommands(cmdSquare);
				}
			} catch(CloneNotSupportedException e) {
				e.printStackTrace();
			}
		} else if (selectedShape instanceof HexagonAdapter) {
			DlgModifyHexagon dlgHexagon = new DlgModifyHexagon();
			HexagonAdapter hexagon = (HexagonAdapter) selectedShape;
			
			dlgHexagon.setTmpX(hexagon.getX());
			dlgHexagon.setTmpY(hexagon.getY());
			dlgHexagon.setTmpWidth(hexagon.getR());
			dlgHexagon.setBorderColor(hexagon.getColor());
			dlgHexagon.setAreaColor(hexagon.getAreaColor());
			dlgHexagon.setCheck(false);

			dlgHexagon.getTxtX().setText(Integer.toString(hexagon.getX()));
			dlgHexagon.getTxtY().setText(Integer.toString(hexagon.getY()));
			dlgHexagon.getTxtWidth().setText(Integer.toString(hexagon.getR()));
			dlgHexagon.getBtnAreaColor().setBackground(hexagon.getAreaColor());
			dlgHexagon.getBtnBorderColor().setBackground(hexagon.getColor());
			
			dlgHexagon.setVisible(true);
			
			try {
			
			HexagonAdapter hexagon2 = (HexagonAdapter) selectedShape.clone();
			
			hexagon2 = new HexagonAdapter (
					dlgHexagon.getTmpX(), 
					dlgHexagon.getTmpY(),
					dlgHexagon.getTmpWidth(),
					dlgHexagon.getBorderColor(),
					dlgHexagon.getAreaColor());
				if (dlgHexagon.isCheck()) {
					frame.getListModel().addElement("Update:" + hexagon.toString() + ", to:" + hexagon2.toString());
					
					CmdUpdateHexagon cmdHexagon = new CmdUpdateHexagon (hexagon, hexagon2); 
					cmdHexagon.execute();
					
					frame.getListModel().addElement("Unselect:" + hexagon.toString());
					
					CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(hexagon, model);
					cmdUnselectShape.execute();
					
					model.addUndoCommands(cmdUnselectShape);
					model.addUndoCommands(cmdHexagon);
				
				}
			} catch(CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		frame.repaint();
	}
	
	public void undo() {
		
		frame.getListModel().addElement("Undo():");

		model.getCommandsUndo().getLast().unexecute();
		
		model.addRedoCommands(model.getCommandsUndo().getLast());
		
		model.removeUndoCommands(model.getCommandsUndo().removeLast());
		
		if (model.getCommandsUndo().size() >0) {
			if (model.getCommandsUndo().getLast() instanceof CmdUnselectShape || model.getCommandsUndo().getLast() instanceof CmdRemoveShape) {
				undo();
			}
		}
			
		frame.repaint();
	}
	
	public void redo() {
		frame.getListModel().addElement("Redo():");

		model.getCommandsRedo().getLast().execute();
		
		model.addUndoCommands(model.getCommandsRedo().getLast());
		
		model.removeRedoCommands(model.getCommandsRedo().removeLast());
		
		if (model.getCommandsRedo().size() >0) {
			if (model.getCommandsRedo().getLast() instanceof CmdUnselectShape || model.getCommandsRedo().getLast() instanceof CmdRemoveShape) {
				redo();
			}
		}
		
		frame.repaint();
	}
	
	public void undoLog() {
		getListModelTmp().addElement("Undo():");

		model.getCommandsLogUndo().getLast().unexecute();
		
		model.getCommandsLogRedo().addLast(model.getCommandsLogUndo().getLast());
		
		model.getCommandsLogUndo().removeLast();
			
		frame.repaint();
	}
	
	public void redoLog() {
		getListModelTmp().addElement("Redo():");

		model.getCommandsLogRedo().getLast().execute();
		
		model.getCommandsLogUndo().addLast(model.getCommandsLogRedo().getLast());
		
		model.getCommandsLogRedo().removeLast();
		
		frame.repaint();
	}
	
	public void toFront() {
		CmdToFront cmdToFront = new CmdToFront (model.getSelected(0), model);
		cmdToFront.execute();
		
		frame.getListModel().addElement("to front:" + model.getSelected(0).toString());
		
		CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(model.getSelected(0), model);
		cmdUnselectShape.execute();
		
		model.addUndoCommands(cmdToFront);
		
		model.addUndoCommands(cmdUnselectShape);
				
		frame.repaint();
		
	}
	public void toBack() {
		CmdToBack cmdToBack = new CmdToBack (model.getSelected(0), model);
		cmdToBack.execute();
		
		frame.getListModel().addElement("to back:" + model.getSelected(0).toString());
		
		CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(model.getSelected(0), model);
		cmdUnselectShape.execute();
		
		model.addUndoCommands(cmdToBack);
				
		model.addUndoCommands(cmdUnselectShape);
		
		frame.repaint();
		
	}
	public void bringToFront() {
		CmdBringToFront cmdBringToFront = new CmdBringToFront (model.getSelected(0), model);
		cmdBringToFront.execute();
		
		frame.getListModel().addElement("bring to front:" + model.getSelected(0).toString());
		
		CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(model.getSelected(0), model);
		cmdUnselectShape.execute();
		
		model.addUndoCommands(cmdBringToFront);
		
		model.addUndoCommands(cmdUnselectShape);
		
		frame.repaint();
	}
	public void bringToBack() {
		CmdBringToBack cmdBringToBack = new CmdBringToBack (model.getSelected(0), model);
		cmdBringToBack.execute();
		
		frame.getListModel().addElement("bring to back:" + model.getSelected(0).toString());
		
		CmdUnselectShape cmdUnselectShape = new CmdUnselectShape(model.getSelected(0), model);
		cmdUnselectShape.execute();
		
		model.addUndoCommands(cmdBringToBack);
		
		
		model.addUndoCommands(cmdUnselectShape);
		
		frame.repaint();
	}
	
	public void openPaint () {
		
		
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setDialogTitle("Open pnt file");
		fileChooser.setEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("paint (.pnt)","pnt");	
		fileChooser.setFileFilter(filter);
		
		if (fileChooser.showOpenDialog(frame.getParent()) == JFileChooser.APPROVE_OPTION) {
			
			System.out.println("Hello open!");
			File file = fileChooser.getSelectedFile();
			FileManager drawingManager = new FileManager(new DrawingOpenAndSave());
			drawingManager.open(model, frame ,file);

			fileChooser.setVisible(false);
			
			//frame.getListModel().clear();s
			//model.getAllSelected().clear();
			
			model.getCommandsUndo().clear();
			model.getCommandsRedo().clear();
			model.setNumOfRedoCommands(0);
			model.setNumOfUndoCommands(0);

			frame.repaint();
		}
		
	}
	
	public void savePaint() {
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setDialogTitle("Save pnt file");
		fileChooser.setEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("paint (.pnt)","pnt");
		fileChooser.setFileFilter(filter);
		
		if (fileChooser.showSaveDialog(frame.getParent()) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Hello save!");
			
			File file = fileChooser.getSelectedFile();

			FileManager fileManager = new FileManager(new DrawingOpenAndSave());
			fileManager.save(model, frame, file);
		}
		
		fileChooser.setVisible(false);
		
		frame.getView().repaint();

	}
	
	public void openLog() {
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setDialogTitle("Open txt file");
		fileChooser.setEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("text (.txt)","txt");
		fileChooser.setFileFilter(filter);
		
		if (fileChooser.showOpenDialog(frame.getParent()) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Hello open!");
			File file = fileChooser.getSelectedFile();
			FileManager drawingManager = new FileManager(new LogOpenAndSave());
			drawingManager.open(model, frame ,file);

			fileChooser.setVisible(false);
			model.getAllSelected().clear();
			model.getCommandsUndo().clear();
			model.getCommandsRedo().clear();
			model.setNumOfRedoCommands(0);
			model.setNumOfUndoCommands(0);
			frame.getBtnDrawLog().setEnabled(true);
			frame.repaint();
			
		}
		
	}
	
	public void saveLog() {
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setDialogTitle("Save txt file");
		fileChooser.setEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("text (.txt)","txt");
		fileChooser.setFileFilter(filter);
		
		if (fileChooser.showSaveDialog(frame.getParent()) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Hello save txt!");
			
			File file = fileChooser.getSelectedFile();

			FileManager fileManager = new FileManager(new LogOpenAndSave());
			fileManager.save(model, frame, file);
		}
		
		fileChooser.setVisible(false);
		
		frame.getView().repaint();
		
	}
	
	public void drawLog() {
		
		DefaultListModel<String> defaultList = frame.getListModel();
		
		if (defaultList.size()-1 < currentDrawLog) {
			setCurrentDrawLog(0); 
			frame.getBtnDrawLog().setEnabled(false);
		} else {
			String [] commands = defaultList.get(getCurrentDrawLog()).split(":");
		
			try {
				currentDrawLog++;
				logCommand(commands);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			
		}
		
		if (defaultList.size()-1 < currentDrawLog) {
			setCurrentDrawLog(0); 
			frame.getBtnDrawLog().setEnabled(false);
		}
		frame.repaint();
	}


	public void logCommand( String [] commands) throws CloneNotSupportedException {
		
		if (commands[0].equals("Undo()")) {
			undoLog();
		} else if (commands[0].equals("Redo()")) {
			redoLog();
		} else if(commands[1].equals("Point")) {
			String [] shape = commands[2].split(",");
			Point point = new Point (Integer.parseInt(shape[0]), Integer.parseInt(shape[1]));
			point.setColor(new Color (Integer.parseInt(shape[2])));
			
			if (commands[0].equals("Update")) {
				shape = commands[4].split(",");
				
				
				Point clonePoint = (Point) point.clone();
				clonePoint = new Point(Integer.parseInt(shape[0]), 
									Integer.parseInt(shape[1]), 
									new Color (Integer.parseInt(shape[2])));
				
				
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Point) {
						if (point.equals(model.get(i))) {
							CmdUpdatePoint cmdPoint = new CmdUpdatePoint ((Point) (model.get(i)), clonePoint); 
							cmdPoint.execute();
							getListModelTmp().addElement("Update:" + ((Point) (model.get(i))).toString());

							model.getCommandsLogUndo().addLast(cmdPoint);
						}
					}
				}
				
				drawLog();
				
			} else if (commands[0].equals("Delete")) {
				getListModelTmp().addElement("Delete:" + point.toString());

				CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(point, model);
				cmdRemoveShape.execute();
				model.getCommandsLogUndo().addLast(cmdRemoveShape);
				
				drawLog();
				
			} else if (commands[0].equals("Draw")){
				getListModelTmp().addElement("Draw:" + point.toString());

				CmdAddShape cmdAddShape = new CmdAddShape(point, model);
				cmdAddShape.execute();
				model.getCommandsLogUndo().addLast(cmdAddShape);

			} else if (commands[0].contains("elect") ) {
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Point) {
						if (point.equals(model.get(i))) {
							if (commands[0].equals("Unselect")){
								getListModelTmp().addElement("Unselect:" + ((Point) (model.get(i))).toString());

								CmdUnselectShape cmdUnelectShape = new CmdUnselectShape(model.get(i), model);
								cmdUnelectShape.execute();
								model.getCommandsLogUndo().addLast(cmdUnelectShape);							
							} else {
								getListModelTmp().addElement("Select:" + ((Point) (model.get(i))).toString());

								CmdSelectShape cmdSelectShape = new CmdSelectShape(model.get(i), model);
								cmdSelectShape.execute();
								model.getCommandsLogUndo().addLast(cmdSelectShape);
								
							}
							break;
						}
					}
				}
			
			} else if (commands[0].equals("bring to front")) {
				getListModelTmp().addElement("bring to front:" + point.toString());

				CmdBringToFront cmdBringToFront = new CmdBringToFront (point, model);
				cmdBringToFront.execute();
				model.getCommandsLogUndo().addLast(cmdBringToFront);

			} else if (commands[0].equals("bring to back")) {
				getListModelTmp().addElement("bring to back:" + point.toString());

				CmdBringToBack cmdBringToBack = new CmdBringToBack (point, model);
				cmdBringToBack.execute();
				model.getCommandsLogUndo().addLast(cmdBringToBack);
			} else if (commands[0].equals("to front")) {
				getListModelTmp().addElement("to front:" + point.toString());

				CmdToFront cmdToFront = new CmdToFront (point, model);
				cmdToFront.execute();
				model.getCommandsLogUndo().addLast(cmdToFront);

			} else if (commands[0].equals("to back")) {
				getListModelTmp().addElement("to back:" + point.toString());

				CmdToBack cmdToBack = new CmdToBack (point, model);
				cmdToBack.execute();
				model.getCommandsLogUndo().addLast(cmdToBack);

			} else {
				System.out.println("Error command!");
			}

		} else if(commands[1].equals("Line")) {
			
			String [] shape = commands[2].split(",");
			
			Point point1 = new Point (Integer.parseInt(shape[0]), Integer.parseInt(shape[1]));
			Point point2 = new Point (Integer.parseInt(shape[2]), Integer.parseInt(shape[3]));
						
			Line line = new Line(point1, point2);
			
			line.setColor(new Color (Integer.parseInt(shape[4])));
			
			if (commands[0].equals("Update")) {
				shape = commands[4].split(",");
				
				Line cloneLine = (Line) line.clone();
				cloneLine = new Line(new Point(Integer.parseInt(shape[0]), Integer.parseInt(shape[1])),
									 new Point(Integer.parseInt(shape[2]), Integer.parseInt(shape[3])),
									 new Color (Integer.parseInt(shape[4])));
				
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Line) {
						if (line.equals(model.get(i))) {
							CmdUpdateLine cmdLine = new CmdUpdateLine ((Line) (model.get(i)), cloneLine); 
							cmdLine.execute();
							model.getCommandsLogUndo().addLast(cmdLine);
							getListModelTmp().addElement("Update:" + ((Line) (model.get(i))).toString());

						}
					}
				}

			} else if (commands[0].equals("Delete")) {
				getListModelTmp().addElement("Delete:" + line.toString());
	
				CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(line, model);
				cmdRemoveShape.execute();
				model.getCommandsLogUndo().addLast(cmdRemoveShape);

			} else if (commands[0].equals("Draw")){
				getListModelTmp().addElement("Draw:" + line.toString());
	
				CmdAddShape cmdAddShape = new CmdAddShape(line, model);
				cmdAddShape.execute();
				model.getCommandsLogUndo().addLast(cmdAddShape);

			} else if (commands[0].contains("elect")) {
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Line) {
						if (line.equals(model.get(i))) {
							if (commands[0].equals("Unselect")){
								CmdUnselectShape cmdUnelectShape = new CmdUnselectShape(model.get(i), model);
								cmdUnelectShape.execute();
								model.getCommandsLogUndo().addLast(cmdUnelectShape);
								getListModelTmp().addElement("Unselect:" + ((Line) (model.get(i))).toString());

							} else {
								CmdSelectShape cmdSelectShape = new CmdSelectShape(model.get(i), model);
								cmdSelectShape.execute();
								model.getCommandsLogUndo().addLast(cmdSelectShape);
								getListModelTmp().addElement("Select:" + ((Line) (model.get(i))).toString());
								break;
							}
						}
					}
				}

			} else if (commands[0].equals("bring to front")) {
				getListModelTmp().addElement("bring to front:" + line.toString());

				CmdBringToFront cmdBringToFront = new CmdBringToFront (line, model);
				cmdBringToFront.execute();
				model.getCommandsLogUndo().addLast(cmdBringToFront);

			} else if (commands[0].equals("bring to back")) {
				getListModelTmp().addElement("bring to back:" + line.toString());

				CmdBringToBack cmdBringToBack = new CmdBringToBack (line, model);
				cmdBringToBack.execute();
				model.getCommandsLogUndo().addLast(cmdBringToBack);

			} else if (commands[0].equals("to front")) {
				getListModelTmp().addElement("to front:" + line.toString());

				CmdToFront cmdToFront = new CmdToFront (line, model);
				cmdToFront.execute();
				model.getCommandsLogUndo().addLast(cmdToFront);

			} else if (commands[0].equals("to back")) {
				getListModelTmp().addElement("to back:" + line.toString());

				CmdToBack cmdToBack = new CmdToBack (line, model);
				cmdToBack.execute();
				model.getCommandsLogUndo().addLast(cmdToBack);

			} else {
				System.out.println("Error command!");
			}

		}  else if(commands[1].equals("Circle")) {
			
			String [] shape = commands[2].split(",");
			
			Point point1 = new Point (Integer.parseInt(shape[0]), Integer.parseInt(shape[1]));
		
			int r = Integer.parseInt(shape[2]); 

			Circle circle = new Circle(point1, r, 
					new Color (Integer.parseInt(shape[3])),
					new Color (Integer.parseInt(shape[4])));
			
			if (commands[0].equals("Update")) {
				shape = commands[4].split(",");
				
				Circle cloneCircle = (Circle) circle.clone();
				cloneCircle = new Circle(new Point(Integer.parseInt(shape[0]), Integer.parseInt(shape[1])),
									 Integer.parseInt(shape[2]), 
									 new Color (Integer.parseInt(shape[3])),
									 new Color (Integer.parseInt(shape[4])));
				
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Circle) {
						if (circle.equals(model.get(i))) {
							CmdUpdateCircle cmdCircle = new CmdUpdateCircle ((Circle) (model.get(i)), cloneCircle); 
							cmdCircle.execute();
							model.getCommandsLogUndo().addLast(cmdCircle);
							getListModelTmp().addElement("Update:" + ((Circle) (model.get(i))).toString());

						}
					}
				}

			} else if (commands[0].equals("Delete")) {
				getListModelTmp().addElement("Delete:" + circle.toString());

				CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(circle, model);
				cmdRemoveShape.execute();
				model.getCommandsLogUndo().addLast(cmdRemoveShape);

			} else if (commands[0].equals("Draw")){
				getListModelTmp().addElement("Draw:" + circle.toString());

				CmdAddShape cmdAddShape = new CmdAddShape(circle, model);
				cmdAddShape.execute();
				model.getCommandsLogUndo().addLast(cmdAddShape);

			} else if (commands[0].contains("elect")) {
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Circle) {
						if (commands[0].equals("Unselect")){
							CmdUnselectShape cmdUnelectShape = new CmdUnselectShape(model.get(i), model);
							cmdUnelectShape.execute();
							model.getCommandsLogUndo().addLast(cmdUnelectShape);
							getListModelTmp().addElement("Unselect:" + ((Circle) (model.get(i))).toString());

						} else {
							CmdSelectShape cmdSelectShape = new CmdSelectShape(model.get(i), model);
							cmdSelectShape.execute();
							model.getCommandsLogUndo().addLast(cmdSelectShape);
							getListModelTmp().addElement("Select:" + ((Circle) (model.get(i))).toString());
							
						}
						break;
					}
				}
				

			} else if (commands[0].equals("bring to front")) {
				getListModelTmp().addElement("bring to front:" + circle.toString());

				CmdBringToFront cmdBringToFront = new CmdBringToFront (circle, model);
				cmdBringToFront.execute();
				model.getCommandsLogUndo().addLast(cmdBringToFront);

			} else if (commands[0].equals("bring to back")) {
				getListModelTmp().addElement("bring to back:" + circle.toString());

				CmdBringToBack cmdBringToBack = new CmdBringToBack (circle, model);
				cmdBringToBack.execute();
				model.getCommandsLogUndo().addLast(cmdBringToBack);

			} else if (commands[0].equals("to front")) {
				getListModelTmp().addElement("to front:" + circle.toString());

				CmdToFront cmdToFront = new CmdToFront (circle, model);
				cmdToFront.execute();
				model.getCommandsLogUndo().addLast(cmdToFront);

			} else if (commands[0].equals("to back")) {
				getListModelTmp().addElement("to back:" + circle.toString());

				CmdToBack cmdToBack = new CmdToBack (circle, model);
				cmdToBack.execute();
				model.getCommandsLogUndo().addLast(cmdToBack);

			} else {
				System.out.println("Error command!");
			}

		}  else if(commands[1].equals("Rectangle")) {
			
			String [] shape = commands[2].split(",");
			
			Point point1 = new Point (Integer.parseInt(shape[0]), Integer.parseInt(shape[1]));
		
			int length = Integer.parseInt(shape[2]); 
			int width = Integer.parseInt(shape[3]);

			Rectangle rectangle = new Rectangle(point1, length, width, 
					new Color (Integer.parseInt(shape[4])),
					new Color (Integer.parseInt(shape[5])));
			
			if (commands[0].equals("Update")) {
				shape = commands[4].split(",");
				
				Rectangle cloneRectangle = (Rectangle) rectangle.clone();
				cloneRectangle = new Rectangle(new Point(Integer.parseInt(shape[0]), Integer.parseInt(shape[1])),
									 Integer.parseInt(shape[2]), 
									 Integer.parseInt(shape[3]),
									 new Color (Integer.parseInt(shape[4])),
									 new Color (Integer.parseInt(shape[5])));
				
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Rectangle) {
						if (rectangle.equals(model.get(i))) {
							CmdUpdateRectangle cmdRectangle = new CmdUpdateRectangle ((Rectangle) (model.get(i)), cloneRectangle); 
							cmdRectangle.execute();
							model.getCommandsLogUndo().addLast(cmdRectangle);
							
							getListModelTmp().addElement("Update:" + ((Rectangle) (model.get(i))).toString());

						}
					}
				}
				
			} else if (commands[0].equals("Delete")) {
				getListModelTmp().addElement("Delete:" + rectangle.toString());

				CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(rectangle, model);
				cmdRemoveShape.execute();
				model.getCommandsLogUndo().addLast(cmdRemoveShape);

			} else if (commands[0].equals("Draw")){
				getListModelTmp().addElement("Draw:" + rectangle.toString());

				CmdAddShape cmdAddShape = new CmdAddShape(rectangle, model);
				cmdAddShape.execute();
				model.getCommandsLogUndo().addLast(cmdAddShape);

			} else if (commands[0].contains("elect")) {
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Rectangle) {
						if (commands[0].equals("Unselect")){
							CmdUnselectShape cmdUnelectShape = new CmdUnselectShape(model.get(i), model);
							cmdUnelectShape.execute();
							model.getCommandsLogUndo().addLast(cmdUnelectShape);
							getListModelTmp().addElement("Unselect:" + ((Rectangle) (model.get(i))).toString());

						} else {
							CmdSelectShape cmdSelectShape = new CmdSelectShape(model.get(i), model);
							cmdSelectShape.execute();
							model.getCommandsLogUndo().addLast(cmdSelectShape);
							getListModelTmp().addElement("Select:" + ((Rectangle) (model.get(i))).toString());

						}
						break;
					}
				}

			} else if (commands[0].equals("bring to front")) {
				getListModelTmp().addElement("bring to front:" + rectangle.toString());

				CmdBringToFront cmdBringToFront = new CmdBringToFront (rectangle, model);
				cmdBringToFront.execute();
				model.getCommandsLogUndo().addLast(cmdBringToFront);

			} else if (commands[0].equals("bring to back")) {
				getListModelTmp().addElement("bring to back:" + rectangle.toString());

				CmdBringToBack cmdBringToBack = new CmdBringToBack (rectangle, model);
				cmdBringToBack.execute();
				model.getCommandsLogUndo().addLast(cmdBringToBack);

			} else if (commands[0].equals("to front")) {
				getListModelTmp().addElement("to front:" + rectangle.toString());

				CmdToFront cmdToFront = new CmdToFront (rectangle, model);
				cmdToFront.execute();
				model.getCommandsLogUndo().addLast(cmdToFront);

			} else if (commands[0].equals("to back")) {
				getListModelTmp().addElement("to back:" + rectangle.toString());

				CmdToBack cmdToBack = new CmdToBack (rectangle, model);
				cmdToBack.execute();
				model.getCommandsLogUndo().addLast(cmdToBack);

			} else {
				System.out.println("Error command!");
			}
										
		} else if(commands[1].equals("Square")) {
			
			String [] shape = commands[2].split(",");
			
			Point point1 = new Point (Integer.parseInt(shape[0]), Integer.parseInt(shape[1]));
		
			int length = Integer.parseInt(shape[2]); 

			Square square = new Square(point1, length, 
					new Color (Integer.parseInt(shape[3])),
					new Color (Integer.parseInt(shape[4])));
			
			if (commands[0].equals("Update")) {
				shape = commands[4].split(",");
				
				Square cloneSquare = (Square) square.clone();
				cloneSquare = new Square(new Point(Integer.parseInt(shape[0]), Integer.parseInt(shape[1])),
									 Integer.parseInt(shape[2]), 
									 new Color (Integer.parseInt(shape[3])),
									 new Color (Integer.parseInt(shape[4])));
				
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Square) {
						if (square.equals(model.get(i))) {
							CmdUpdateSquare cmdSquare = new CmdUpdateSquare ((Square) (model.get(i)), cloneSquare); 
							cmdSquare.execute();
							model.getCommandsLogUndo().addLast(cmdSquare);
							getListModelTmp().addElement("Update:" + ((Square) (model.get(i))).toString());

						}
					}
				}

			} else if (commands[0].equals("Delete")) {
				getListModelTmp().addElement("Delete:" + square.toString());

				CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(square, model);
				cmdRemoveShape.execute();
				model.getCommandsLogUndo().addLast(cmdRemoveShape);

			} else if (commands[0].equals("Draw")){
				getListModelTmp().addElement("Draw:" + square.toString());

				CmdAddShape cmdAddShape = new CmdAddShape(square, model);
				cmdAddShape.execute();
				model.getCommandsLogUndo().addLast(cmdAddShape);

			} else if (commands[0].contains("elect")) {
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof Square) {
						if (commands[0].equals("Unselect")){
							CmdUnselectShape cmdUnelectShape = new CmdUnselectShape(model.get(i), model);
							cmdUnelectShape.execute();
							model.getCommandsLogUndo().addLast(cmdUnelectShape);
							getListModelTmp().addElement("Unselect:" + ((Square) (model.get(i))).toString());

						} else {
							CmdSelectShape cmdSelectShape = new CmdSelectShape(model.get(i), model);
							cmdSelectShape.execute();
							model.getCommandsLogUndo().addLast(cmdSelectShape);
							getListModelTmp().addElement("Select:" + ((Square) (model.get(i))).toString());

						}
						break;
					}
				}
				
			} else if (commands[0].equals("bring to front")) {
				getListModelTmp().addElement("bring to front:" + square.toString());

				CmdBringToFront cmdBringToFront = new CmdBringToFront (square, model);
				cmdBringToFront.execute();
				model.getCommandsLogUndo().addLast(cmdBringToFront);

			} else if (commands[0].equals("bring to back")) {
				getListModelTmp().addElement("bring to back:" + square.toString());

					CmdBringToBack cmdBringToBack = new CmdBringToBack (square, model);
					cmdBringToBack.execute();
					model.getCommandsLogUndo().addLast(cmdBringToBack);

			} else if (commands[0].equals("to front")) {
				getListModelTmp().addElement("to front:" + square.toString());

				CmdToFront cmdToFront = new CmdToFront (square, model);
				cmdToFront.execute();
				model.getCommandsLogUndo().addLast(cmdToFront);

			} else if (commands[0].equals("to back")) {
				getListModelTmp().addElement("to back:" + square.toString());

				CmdToBack cmdToBack = new CmdToBack (square, model);
				cmdToBack.execute();
				model.getCommandsLogUndo().addLast(cmdToBack);

			} else {
				System.out.println("Error command!");
			}

		} else if(commands[1].equals("Hexagon")) {
			
			String [] shape = commands[2].split(",");
					
			int length = Integer.parseInt(shape[2]); 

			HexagonAdapter hexagon = new HexagonAdapter(Integer.parseInt(shape[0]), 
														Integer.parseInt(shape[1]),						
														length, 
														new Color (Integer.parseInt(shape[3])),
														new Color (Integer.parseInt(shape[4])));
			
			if (commands[0].equals("Update")) {
				shape = commands[4].split(",");
				
				HexagonAdapter cloneHexagon = (HexagonAdapter) hexagon.clone();
				cloneHexagon = new HexagonAdapter(Integer.parseInt(shape[0]), 
												  Integer.parseInt(shape[1]),
												  Integer.parseInt(shape[2]), 
												  new Color (Integer.parseInt(shape[3])),
												  new Color (Integer.parseInt(shape[4])));
				
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof HexagonAdapter) {
						if (hexagon.equals(model.get(i))) {
							CmdUpdateHexagon cmdHexagon = new CmdUpdateHexagon ((HexagonAdapter) (model.get(i)), cloneHexagon); 
							cmdHexagon.execute();
							model.getCommandsLogUndo().addLast(cmdHexagon);
							
							getListModelTmp().addElement("Update:" + ((HexagonAdapter) (model.get(i))).toString());

						}
					}
				}

			} else if (commands[0].equals("Delete")) {
				getListModelTmp().addElement("Delete:" + hexagon.toString());

				CmdRemoveShape cmdRemoveShape = new CmdRemoveShape(hexagon, model);
				cmdRemoveShape.execute();
				model.getCommandsLogUndo().addLast(cmdRemoveShape);

			} else if (commands[0].equals("Draw")){
				getListModelTmp().addElement("Draw:" + hexagon.toString());

				CmdAddShape cmdAddShape = new CmdAddShape(hexagon, model);
				cmdAddShape.execute();
				model.getCommandsLogUndo().addLast(cmdAddShape);

			} else if (commands[0].contains("elect")) {
				for (int i = 0; i < model.getAll().size(); i++) {
					if (model.get(i) instanceof HexagonAdapter) {
						if (commands[0].equals("Unselect")){
							CmdUnselectShape cmdUnelectShape = new CmdUnselectShape(model.get(i), model);
							cmdUnelectShape.execute();
							model.getCommandsLogUndo().addLast(cmdUnelectShape);
							getListModelTmp().addElement("Unselect:" + ((HexagonAdapter) (model.get(i))).toString());


						} else {
							CmdSelectShape cmdSelectShape = new CmdSelectShape(model.get(i), model);
							cmdSelectShape.execute();
							model.getCommandsLogUndo().addLast(cmdSelectShape);
							getListModelTmp().addElement("Select:" + ((HexagonAdapter) (model.get(i))).toString());

						}
					}
				}

			} else if (commands[0].equals("bring to front")) {
				getListModelTmp().addElement("bring to front:" + hexagon.toString());

				CmdBringToFront cmdBringToFront = new CmdBringToFront (hexagon, model);
				cmdBringToFront.execute();
				model.getCommandsLogUndo().addLast(cmdBringToFront);

			} else if (commands[0].equals("bring to back")) {
				getListModelTmp().addElement("bring to back:" + hexagon.toString());

				CmdBringToBack cmdBringToBack = new CmdBringToBack (hexagon, model);
				cmdBringToBack.execute();
				model.getCommandsLogUndo().addLast(cmdBringToBack);

			} else if (commands[0].equals("to front")) {
				getListModelTmp().addElement("to front:" + hexagon.toString());

				CmdToFront cmdToFront = new CmdToFront (hexagon, model);
				cmdToFront.execute();
				model.getCommandsLogUndo().addLast(cmdToFront);


			} else if (commands[0].equals("to back")) {
				getListModelTmp().addElement("to back:" + hexagon.toString());

				CmdToBack cmdToBack = new CmdToBack (hexagon, model);
				cmdToBack.execute();
				model.getCommandsLogUndo().addLast(cmdToBack);

			}
			else {
				System.out.println("Error command!");
			}

		}
		
		frame.getView().repaint();
	}
	
	public int getCurrentDrawLog() {
		return currentDrawLog;
	}

	public void setCurrentDrawLog(int currentDrawLog) {
		this.currentDrawLog = currentDrawLog;
	}
	
	public void logEnd() {
		//frame.setListModel(getListModelTmp());
		frame.getListModel().clear();
		for (int i = 0; i < getCurrentDrawLog(); i++) {
			frame.getListModel().addElement(getListModelTmp().get(i));
		}
		
		frame.getBtnDrawLog().setEnabled(false);
		
		frame.repaint();
	}
	
	public DefaultListModel<String> getListModelTmp() {
		return listModelTmp;
	}
	public void setListModelTmp (DefaultListModel<String> listModelTmp) {
		this.listModelTmp = listModelTmp;
	}

}
