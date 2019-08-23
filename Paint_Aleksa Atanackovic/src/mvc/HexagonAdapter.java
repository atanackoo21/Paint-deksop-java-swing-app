package mvc;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape{
	private Hexagon hexagon;
	
	public HexagonAdapter() {
		hexagon = new Hexagon (0, 0, 0);
	}
	
	public HexagonAdapter(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public HexagonAdapter(int x, int y, int r) {
		hexagon = new Hexagon (x, y, r);
	
	}
	
	public HexagonAdapter(int x, int y, int r, Color borderColor, Color areaColor) {
		hexagon = new Hexagon (x, y, r);
		this.setAreaColor(areaColor);
		this.setColor(borderColor);
	
	}

	@Override
	public void fill(Graphics g) {
		hexagon.setAreaColor(getAreaColor());
	}
	
	@Override
	public String toString() {
		return "Hexagon:" + hexagon.getX()  +  
		"," + hexagon.getY() + "," + hexagon.getR() + "," 
		+ getColor().getRGB() + "," + getAreaColor().getRGB();
	}	
	
	@Override
	public void draw(Graphics g) {
		hexagon.setBorderColor(getColor());
		fill(g);
		hexagon.paint(g);
	}

	@Override
	public void selected(Graphics g) {
//		g.setColor(Color.BLUE);
//		int x = hexagon.getX();
//		int y = hexagon.getY();
//		int r = hexagon.getR();
//
//		new Line (new Point(x-r,y), new Point(x+r, y)).selected(g);
//		new Line (new Point(x+(r/2), y + (int)(Math.round((Math.sqrt(3)/2)))), new Point(x+(r/2), y - (int)(Math.round((Math.sqrt(3)/2))))).selected(g);
//		new Line (new Point(x-(r/2), y + (int)(Math.round((Math.sqrt(3)/2)))), new Point(x-(r/2), y - (int)(Math.round((Math.sqrt(3)/2))))).selected(g);

	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	
	
	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		//super.setSelected(selected);
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		super.setColor(color);
	}
	
	public Color getAreaColor() {
		return hexagon.getAreaColor();
	}
	
	public void setAreaColor(Color color) {
		hexagon.setAreaColor(color);
		super.setAreaColor(color);
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}
	public void setY(int y) {
		hexagon.setY(y);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter temp = (HexagonAdapter) obj;
		if (this.getX() == temp.getX() && this.getY() == temp.getY() && this.getR() == temp.getR()
				&& this.getColor().equals(temp.getColor()) && this.getAreaColor().equals(temp.getAreaColor()))
				return true;
			else
				return false;
		} else
			return false;
	}
	

}
