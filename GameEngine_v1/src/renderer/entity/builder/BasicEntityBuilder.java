package renderer.entity.builder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.point.Point3D;
import renderer.point.Vector3;
import renderer.shapes.MyPolygon;
import renderer.shapes.MyPolyhedron;

public class BasicEntityBuilder {
	
	public static IEntity createCube( double size, Point3D position, Vector3 direction ) {
		
		Point3D p1 = new Point3D( position.x + size/2, position.y + -size/2, position.z + -size/2 );
		Point3D p2 = new Point3D( position.x + size/2, position.y + size/2, position.z + -size/2 );
		Point3D p3 = new Point3D( position.x + size/2, position.y + size/2, position.z + size/2 );
		Point3D p4 = new Point3D( position.x + size/2, position.y + -size/2, position.z + size/2 );
		Point3D p5 = new Point3D( position.x + -size/2, position.y + -size/2, position.z + -size/2 );
		Point3D p6 = new Point3D( position.x + -size/2, position.y + size/2, position.z + -size/2 );
		Point3D p7 = new Point3D( position.x + -size/2, position.y + size/2, position.z + size/2 );
		Point3D p8 = new Point3D( position.x + -size/2, position.y + -size/2, position.z + size/2 );
		
		MyPolyhedron tetra = new MyPolyhedron( 
			new MyPolygon( Color.BLUE, p5, p6, p7, p8 ),
			new MyPolygon( Color.WHITE, p1, p2, p6, p5 ),
			new MyPolygon( Color.YELLOW, p1, p5, p8, p4 ),
			new MyPolygon( Color.GREEN, p2, p6, p7, p3 ),
			new MyPolygon( Color.ORANGE, p4, p3, p7, p8 ),
			new MyPolygon( Color.RED, p1, p2, p3, p4 )
		);
		
		List<MyPolyhedron> tetras = new ArrayList<MyPolyhedron>();
		tetras.add(tetra);
		return new Entity( tetras );
		
	}
	
	public static IEntity createSphere( double diameter, int resolution, Point3D position, Vector3 direction ) {
		
		double radius = diameter / 2;
		
		List<MyPolyhedron> polyhedrons = new ArrayList<MyPolyhedron>();
		List<MyPolygon> polygons = new ArrayList<MyPolygon>();
		
		Point3D bottom = new Point3D(position.x, position.y, position.z - radius);
		Point3D top = new Point3D(position.x, position.y, position.z + radius);
		
		Point3D[][] points = new Point3D[resolution - 1][resolution];
		
		for (int i = 1; i < resolution; i++) {
			double theta = (Math.PI / resolution) * i;
			double zPos = -Math.cos(theta) * radius; //TODO: try change to sine and see what shape it makes
			double currentRadius = Math.abs( Math.sin(theta) * radius );
			for (int j = 0; j < resolution; j++) {
				double alpha = 2 * (Math.PI / resolution) * j;
				double xPos = -Math.sin(alpha) * currentRadius;
				double yPos = Math.cos(alpha) * currentRadius;
				points[i-1][j] = new Point3D(position.x + xPos, position.y + yPos, position.z + zPos);
			}
		}
		
		for (int i = 1; i <= resolution; i++) {
			for (int j = 0; j < resolution; j++) {
				if (i == 1) {
					polygons.add(new MyPolygon( 
						points[i-1][j], 
						points[i-1][ (j+1) % resolution],
						bottom
					));
				} else if (i == resolution) {
					polygons.add(new MyPolygon( 
						points[i-2][ (j+1) % resolution],
						points[i-2][j], 
						top
					));
				} else {
					polygons.add(new MyPolygon( 
						points[i-1][j], 
						points[i-1][ (j+1) % resolution], 
						points[i-2][ (j+1) % resolution],
						points[i-2][j]
					));
				}
			}
		}
		
		MyPolygon[] polygonArray = new MyPolygon[polygons.size()];
		polygonArray = polygons.toArray(polygonArray);
		
		MyPolyhedron polyhedron = new MyPolyhedron(polygonArray);
		// polyhedron.setColor(null);
		polyhedrons.add(polyhedron);
		
		return new Entity( polyhedrons );
		
	}
	
}
