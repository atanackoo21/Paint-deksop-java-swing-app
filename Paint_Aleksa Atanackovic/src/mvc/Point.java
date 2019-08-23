package mvc;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private int x;
	private int y;
	private Color color;

	public Point() {

	}
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x - 1, y - 1, x + 1, y + 1);
		g.drawLine(x - 1, y + 1, x + 1, y - 1);
		
		if (isSelected())
			selected(g);
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(getColor());
		g.drawRect(x - 3, y - 3, 6, 6);
	}

	@Override
	public boolean contains(int x, int y) {
		Point click = new Point(x, y);
		if (click.distance(this) <= 2)
			return true;
		else
			return false;
	}
	
	public double distance(Point point2) {
		double dx = x - point2.getX();
		double dy = y - point2.getY();
		double result = Math.sqrt(dx * dx + dy * dy);
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point temp = (Point) obj;
			if (this.x == temp.getX() && this.y == temp.getY())
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	  @Override 
	public String toString() { 
		return "Point:" + x + "," + y + "," + color.getRGB(); 
	}
	
}
