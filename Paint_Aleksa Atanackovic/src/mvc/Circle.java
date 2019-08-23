package mvc;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	
	private Point center;
	private int r;
	
	public Circle() {

	}

	public Circle(Point center, int r) {
		this.center = center;
		this.r = r;
	}

	public Circle(Point center, int r, Color color) {
		this(center, r);
		setColor(color);
	}
	
	public Circle(Point center, int r, Color color, Color areaColor) {
		this(center, r);
		setColor(color);
		this.setAreaColor(areaColor);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(center.getX() - r, center.getY() - r, 2 * r, r * 2);
		if (isSelected())
			selected(g);
		fill(g);
		
	}
	
	@Override
	public String toString() {
		return "Circle:" + center.getX() + "," + center.getY() + "," + r + "," + getColor().getRGB() + ","
				+ getAreaColor().getRGB();
	}

	@Override
	public void selected(Graphics g) {
		new Line(new Point(center.getX(), center.getY() - r), new Point(center.getX(), center.getY() + r))
		.selected(g);
		new Line(new Point(center.getX() - r, center.getY()), new Point(center.getX() + r, center.getY()))
		.selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		Point click = new Point(x, y);
		if (click.distance(center) <= r)
			return true;
		else
			return false;
	}
	
	public void fill(Graphics g) {
		g.setColor(getAreaColor());
		g.fillOval(center.getX() - r + 1, center.getY() - r + 1, 2 * r - 2, r * 2 - 2);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle temp = (Circle) obj;
			if (this.center.equals(temp.center) && this.r == temp.r)
				return true;
			else
				return false;
		} else
			return false;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
	
	

}
