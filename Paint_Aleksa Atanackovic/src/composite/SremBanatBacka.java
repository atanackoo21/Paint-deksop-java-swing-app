package composite;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import mvc.Shape;

public class SremBanatBacka extends Shape {
	private ArrayList<Shape> sremBanatBacka;

	public SremBanatBacka() {
		sremBanatBacka = new ArrayList<Shape>();
	}

	public void addComponent(Shape s) {
		sremBanatBacka.add(s);
	}

	public void removeComponent(Shape s) {
		sremBanatBacka.remove(s);
	}

	@Override
	public void draw(Graphics g) {
		Iterator<Shape> it = sremBanatBacka.iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}

	}

	@Override
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
