package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;


import java.awt.BorderLayout;


public class DrawingView extends JPanel {
	
	public DrawingView() {
		setLayout(new BorderLayout(0, 0));
	}
	private DrawingModel model;

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getAll().iterator();

			while (it.hasNext()) {
				it.next().draw(g);
			}
		}
	}

}
