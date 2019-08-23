package mvc;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends SurfaceShape{
	
	protected Point upLeft;
	protected int length;
	
	public Square() {

	}

	public Square(Point upLeft, int length) {
		this.upLeft = upLeft;
		this.length = length;
	}

	public Square(Point upLeft, int length, Color color) {
		
		this(upLeft, length);
		setColor(color);
		
	}
	
public Square(Point upLeft, int length, Color color, Color areaColor) {
		
		this(upLeft, length);
		setColor(color);
		setAreaColor(areaColor);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square temp = (Square) obj;
		if (this.upLeft.equals(temp.upLeft) && this.length == temp.length 
				&& this.getColor().equals(temp.getColor()) && this.getAreaColor().equals(temp.getAreaColor()))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), length, length);
		fill(g);
		if (isSelected())
			selected(g);
	}
	
	public void fill(Graphics g) {
		g.setColor(getAreaColor());
		g.fillRect(upLeft.getX() + 1, upLeft.getY() + 1, length - 1, length - 1);
	}

	@Override
	public void selected(Graphics g) {
		g.setColor(Color.BLUE);
		new Line(getUpLeft(), new Point(getUpLeft().getX() + length, getUpLeft().getY())).selected(g);
		new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY() + length)).selected(g);
		new Line(new Point(getUpLeft().getX() + length, getUpLeft().getY()), diagonal().getEndPoint())
				.selected(g);
		new Line(new Point(getUpLeft().getX(), getUpLeft().getY() + length), diagonal().getEndPoint())
				.selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		if (this.getUpLeft().getX() <= x && x <= (this.getUpLeft().getX() + length)
				&& this.getUpLeft().getY() <= y && y <= (this.getUpLeft().getY() + length))
			return true;
		else
			return false;
	}
	
	public Line diagonal() {
		return new Line(upLeft, new Point(upLeft.getX() + length, upLeft.getY() + length));
	}

	public Point center() {
		return diagonal().centerLine();
	}
	
	public Point getUpLeft() {
		return upLeft;
	}

	public void setUpLeft(Point upLeft) {
		this.upLeft = upLeft;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public String toString() {
		return "Square:" + upLeft.getX() + "," + upLeft.getY() + "," + length + 
			   "," + getColor().getRGB() + "," + getAreaColor().getRGB();
	}

}
