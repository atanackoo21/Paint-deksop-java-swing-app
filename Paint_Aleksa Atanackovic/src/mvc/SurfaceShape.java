package mvc;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape{
	private Color areaColor = Color.WHITE;

	public abstract void fill(Graphics g);

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}
}
