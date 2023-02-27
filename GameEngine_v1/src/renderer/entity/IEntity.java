package renderer.entity;

import java.awt.Color;
import java.util.List;

import renderer.shapes.MyPolygon;
import renderer.shapes.MyPolyhedron;

public interface IEntity {
	void init();
	void update();
	
	void setColor(Color color);
	Color getColor();
	
	List<MyPolyhedron> getPolyhedrons();
	MyPolygon[] getPolygons();
	
	String toString();
}
