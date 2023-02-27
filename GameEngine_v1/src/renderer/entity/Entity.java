package renderer.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.Color;

import renderer.shapes.MyPolygon;
import renderer.shapes.MyPolyhedron;

public class Entity implements IEntity {

	private List<MyPolyhedron> polyhedrons;
	private MyPolygon[] polygons;
	
	public Color color;
	
	public Entity( List<MyPolyhedron> polyhedrons ) {
		this.polyhedrons = polyhedrons;
		List<MyPolygon> tempList = new ArrayList<MyPolygon>();
		for (MyPolyhedron tetra : this.polyhedrons) {
			tempList.addAll( Arrays.asList( tetra.getPolygons() ) );
		}
		this.polygons = new MyPolygon[tempList.size()];
		this.polygons = tempList.toArray(this.polygons);
	}
	
	public List<MyPolyhedron> getPolyhedrons() {
		return this.polyhedrons;
	}
	
	public MyPolygon[] getPolygons() {
		return this.polygons;
	}
	
	@Override
	public void setColor( Color color ) {
		this.color = color;
	}
	
	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void init() { }

	@Override
	public void update() { }
	
	@Override
	public String toString() {
		String baseString = "Entity | %s | Polyhedron Count: %s | Polygon Count: %s";
		return String.format(
			baseString,
			Integer.toHexString(this.hashCode()),
			this.polyhedrons.toArray().length,
			this.polygons.length
		);
	}
	
}
