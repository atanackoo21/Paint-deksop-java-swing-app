package prototype;

import java.awt.Color;

import mvc.Point;

public class LineDeep implements Cloneable {
	private Point startPoint;
	private Point endPoint;
	private Color color;

	public LineDeep() {

	}

	public LineDeep(Point startPoint, Point endPoint, Color color) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.color = color;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public LineDeep clone() {
		LineDeep ld = new LineDeep();
		ld.setStartPoint(
				new Point(this.getStartPoint().getX(), this.getStartPoint().getY(), this.getStartPoint().getColor()));
		ld.setEndPoint(new Point(this.getEndPoint().getX(), this.getEndPoint().getY(), this.getEndPoint().getColor()));
		ld.setColor(this.getColor());
		return ld;
	}

}
