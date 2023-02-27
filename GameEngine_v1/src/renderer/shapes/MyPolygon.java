package renderer.shapes;

import java.awt.Color;

import renderer.point.Point3D;

public class MyPolygon {

	private Color color;
	private Point3D[] points;
	
	public MyPolygon(Point3D... points) {
		// this.color = Color.WHITE;
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			Point3D p = points[i];
			this.points[i] = new Point3D(p.x, p.y, p.z);
		}
	}
	
	public MyPolygon(Color color, Point3D... points) {
		this.color = color;
		this.points = new Point3D[points.length];
		for (int i = 0; i < points.length; i++) {
			Point3D p = points[i];
			this.points[i] = new Point3D(p.x, p.y, p.z);
		}
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor( Color color ) {
		this.color = color;
	}
	
}
