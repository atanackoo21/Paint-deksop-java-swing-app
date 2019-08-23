package mvc;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Square{
	
	private int width;
	
	public Rectangle() {

	}
	
	public Rectangle(Point upLeft, int length, int width) {
		super(upLeft, length);
		this.width = width;
	}

	public Rectangle(Point upLeft, int length, int width, Color color) {
		this(upLeft, length, width);
		setColor(color);
	}
	
	public Rectangle(Point upLeft, int length, int width, Color borderColor, Color areaColor) {
		this(upLeft, length, width);
		setColor(borderColor);
		setAreaColor(areaColor);
	}
	
	public Line diagonal() {
		return new Line(upLeft, new Point(upLeft.getX() + getLength(), upLeft.getY() + width));
	}

	public Point center() {
		return diagonal().centerLine();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle temp = (Rectangle) obj;
			if (this.upLeft.equals(temp.upLeft) && this.width == temp.width
					&& this.getLength() == temp.getLength() && this.getColor().equals(temp.getColor()) 
					&& this.getAreaColor().equals(temp.getAreaColor()))
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean contains(int x, int y) {
		if (this.getUpLeft().getX() <= x && x <= (this.getUpLeft().getX() + this.getLength())
				&& this.getUpLeft().getY() <= y && y <= (this.getUpLeft().getY() + width))
			return true;
		else
			return false;
	}

	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(getUpLeft(), new Point(getUpLeft().getX() + getLength(), getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY() + width)).selected(g);
		new Line(new Point(getUpLeft().getX() + getLength(), getUpLeft().getY()), diagonal().getEndPoint())
				.selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY() + width), diagonal().getEndPoint())
				.selected(g);
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), getLength(), width);
		fill(g);
		if (isSelected())
			selected(g);
	}

	public void fill(Graphics g) {
		g.setColor(getAreaColor());
		g.fillRect(upLeft.getX() + 1, upLeft.getY() + 1, getLength() - 1, width - 1);

	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public String toString() {
		return "Rectangle:" + upLeft.getX() + "," + upLeft.getY() + "," + length + "," + width +
			   "," + getColor().getRGB() + "," + getAreaColor().getRGB();
	}
	

}
