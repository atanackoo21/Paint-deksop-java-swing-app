package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape  implements Serializable, Cloneable{

	private Color color = Color.BLACK;
	private boolean selected;

	public Shape() {
		
	}

	public Shape(Color color) {
		super();
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void selected(Graphics g);

	public abstract boolean contains(int x, int y);
	
	public Object clone() throws CloneNotSupportedException {
		try {
			return (Shape) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
