package mvc;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
	
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, Color color) {
		super(color);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		
	}
	
	public Point centerLine() {
		int centerX = (startPoint.getX() + endPoint.getX()) / 2;
		int centerY = (startPoint.getY() + endPoint.getY()) / 2;
		return new Point(centerX, centerY);
	}


	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(getStartPoint().getX(), getStartPoint().getY(), getEndPoint().getX(), getEndPoint().getY());
		
		if (isSelected())
			selected(g);
	}

	@Override
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		startPoint.selected(g);
		endPoint.selected(g);
		centerLine().selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		Point click = new Point(x, y);
		if (click.distance(startPoint) + click.distance(endPoint) - this.length() <= 0.5)
			return true;
		else
			return false;
	}
	
	public double length() {
		return startPoint.distance(endPoint);
	}
	
	public Point getStartPoint() {
		return startPoint;
	}


	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}


	public Point getEndPoint() {
		return endPoint;
	}


	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	
	public String toString() {
		return "Line:" + startPoint.getX() + "," + startPoint.getY() + "," + endPoint.getX() + "," + endPoint.getY()
		+ ","+ this.getColor().getRGB();
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			if (line.getStartPoint().equals(startPoint) && line.getEndPoint().equals(endPoint)
					&& line.getColor().equals(getColor()))
				return true;
			else
				return false;
		} else
			return false;
	}
	
}
