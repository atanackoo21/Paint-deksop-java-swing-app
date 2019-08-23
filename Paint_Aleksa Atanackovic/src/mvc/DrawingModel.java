package mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import command.Command;
import observerPaint.Observer;
import observerPaint.Subject;


public class DrawingModel implements Subject{

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<>();
	private int numOfSelected;
	private List<Observer> observers;
	private int numOfElements = 0;
	private LinkedList<Command> commandsUndo = new LinkedList<Command>();
	private LinkedList<Command> commandsRedo = new LinkedList<Command>();
	private int numOfUndoCommands;
	private int numOfRedoCommands;
	
	private LinkedList<Command> commandsLogUndo = new LinkedList<Command>();
	private LinkedList<Command> commandsLogRedo = new LinkedList<Command>();
	
	public DrawingModel() {
		observers = new ArrayList<Observer>();
	}

	public void addSelected(Shape e) {
		selectedShapes.add(e);
		setNumOfSelected(selectedShapes.size());
	}

	public void removeSelected(Shape s) {
		selectedShapes.remove(s);
		setNumOfSelected(selectedShapes.size());
	}
	
	public ArrayList<Shape> getAllSelected() {
		return selectedShapes;
	}

	public Shape getSelected(int i) {
		return selectedShapes.get(i);
	}

	public void add(Shape s) {
		shapes.add(s);
		setNumOfElements(this.getAll().size());
	}

	public void remove(Shape s) {
		shapes.remove(s);
		setNumOfElements(this.getAll().size());
	}

	public ArrayList<Shape> getAll() {
		return shapes;
	}

	public Shape get(int i) {
		return shapes.get(i);
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public int getNumOfSelected() {
		return numOfSelected;
	}

	public void setNumOfSelected(int numOfSelected) {
		this.numOfSelected = numOfSelected;
		notifyObservers();
	}
	
	public LinkedList<Command> getCommandsUndo() {
		return commandsUndo;
	}

	public void setCommandsUndo(LinkedList<Command> commandsUndo) {
		this.commandsUndo = commandsUndo;
	}

	public LinkedList<Command> getCommandsRedo() {
		return commandsRedo;
	}

	public void setCommandsRedo(LinkedList<Command> commandsRedo) {
		this.commandsRedo = commandsRedo;
	}

	
	@Override
	public void notifyObservers() {
		Iterator<Observer> it = observers.iterator();
		while (it.hasNext()) {
			it.next().update(numOfSelected, numOfElements, numOfUndoCommands, numOfRedoCommands);
		}
	}

	public int getNumOfElements() {
		return numOfElements;
	}

	public void setNumOfElements(int numOfElements) {
		this.numOfElements = numOfElements;
		notifyObservers();
	}
	
	// for undo button
	public void addUndoCommands(Command c) {
		commandsUndo.add(c);
		setNumOfUndoCommands(this.getCommandsUndo().size());
	}
	
	public void removeUndoCommands(Command c) {
		commandsUndo.remove(c);
		setNumOfUndoCommands(this.getCommandsUndo().size());
	}
	
	//for redo button
	public void addRedoCommands(Command c) {
		commandsRedo.add(c);
		setNumOfRedoCommands(this.getCommandsRedo().size());
	}

	public void removeRedoCommands(Command c) {
		commandsRedo.remove(c);
		setNumOfRedoCommands(this.getCommandsRedo().size());
	}

	public int getNumOfUndoCommands() {
		return numOfUndoCommands;
	}

	public void setNumOfUndoCommands(int numOfUndoCommands) {
		this.numOfUndoCommands = numOfUndoCommands;
		notifyObservers();
	}

	public int getNumOfRedoCommands() {
		return numOfRedoCommands;
	}

	public void setNumOfRedoCommands(int numOfRedoCommands) {
		this.numOfRedoCommands = numOfRedoCommands;
		notifyObservers();
	}
	
	public int getIndexOfShape(Shape s) {
		return shapes.indexOf(s);
	}

	public void addOnIndex(Shape s, int indexOfShape) {
		this.shapes.add(indexOfShape, s);
		setNumOfElements(this.getAll().size());
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public LinkedList<Command> getCommandsLogUndo() {
		return commandsLogUndo;
	}

	public void setCommandsLogUndo(LinkedList<Command> commandsLogUndo) {
		this.commandsLogUndo = commandsLogUndo;
	}

	public LinkedList<Command> getCommandsLogRedo() {
		return commandsLogRedo;
	}

	public void setCommandsLogRedo(LinkedList<Command> commandsLogRedo) {
		this.commandsLogRedo = commandsLogRedo;
	}
	
	
	
}
