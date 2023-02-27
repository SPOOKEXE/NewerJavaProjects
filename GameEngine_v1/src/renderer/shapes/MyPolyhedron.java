package renderer.shapes;

import java.awt.Color;

public class MyPolyhedron {

	private MyPolygon[] polygons;
	private Color color;
	
	public MyPolyhedron( MyPolygon... polygons ) {
		this.polygons = polygons;
	}
	
	public MyPolygon[] getPolygons() {
		return this.polygons;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor( Color color ) {
		this.color = color;
	}
	
}
