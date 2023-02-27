package renderer.shapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.math.Vector3;
import renderer.primitives.Mesh;
import renderer.primitives.Quad;
import renderer.primitives.Triangle;
import renderer.primitives.Vertex;

public class BasicEntityBuilder {
	
	public static IEntity createCube( double size, Vector3 position, Vector3 direction, Color color ) {
		
		double half_size = size / 2;
		
		Vertex v1 = new Vertex( position.x - half_size, position.y - half_size, position.z - half_size );
		Vertex v2 = new Vertex( position.x - half_size, position.y + half_size, position.z - half_size );
		Vertex v3 = new Vertex( position.x - half_size, position.y + half_size, position.z + half_size );
		Vertex v4 = new Vertex( position.x - half_size, position.y - half_size, position.z + half_size );
		
		Vertex v5 = new Vertex( position.x + half_size, position.y - half_size, position.z - half_size );
		Vertex v6 = new Vertex( position.x + half_size, position.y + half_size, position.z - half_size );
		Vertex v7 = new Vertex( position.x + half_size, position.y + half_size, position.z + half_size );
		Vertex v8 = new Vertex( position.x + half_size, position.y - half_size, position.z + half_size );
		
		Quad faceLeft = new Quad( v1, v2, v3, v4, color);
		Quad faceRight = new Quad( v5, v6, v7, v8, color);
		Quad faceFront = new Quad( v1, v2, v6, v5, color);
		Quad faceBack = new Quad( v4, v3, v7, v8, color);
		Quad faceUp = new Quad( v2, v3, v6, v7, color);
		Quad faceDown = new Quad( v1, v4, v5, v8, color);
		
		Mesh newMesh = new Mesh(faceLeft, faceRight, faceFront, faceBack, faceUp, faceDown);
		
		return new Entity(newMesh);
	}
	
	public static IEntity createCube( double size, Vector3 position, Vector3 direction) {
		return createCube(size, position, direction, Color.RED);
	}
	
	public static IEntity createSphere( double diameter, int resolution, Vector3 position, Vector3 direction, Color color ) {
	
		double radius = diameter / 2;
		
		List<Triangle> tris = new ArrayList<Triangle>();
		
		Vector3 bottom = new Vector3(position.x, position.y, position.z - radius);
		Vector3 top = new Vector3(position.x, position.y, position.z + radius);
		
		Vector3[][] points = new Vector3[resolution - 1][resolution];
		
		for (int i = 1; i < resolution; i++) {
			double theta = (Math.PI / resolution) * i;
			double zPos = -Math.cos(theta) * radius; //TODO: try change to sine and see what shape it makes
			double currentRadius = Math.abs( Math.sin(theta) * radius );
			for (int j = 0; j < resolution; j++) {
				double alpha = 2 * (Math.PI / resolution) * j;
				double xPos = -Math.sin(alpha) * currentRadius;
				double yPos = Math.cos(alpha) * currentRadius;
				points[i-1][j] = new Vector3(position.x + xPos, position.y + yPos, position.z + zPos);
			}
		}
		
		for (int i = 1; i <= resolution; i++) {
			for (int j = 0; j < resolution; j++) {
				if (i == 1) {
					tris.add(new Triangle( 
						points[i-1][j], 
						points[i-1][ (j+1) % resolution],
						bottom
					));
				} else if (i == resolution) {
					tris.add(new Triangle( 
						points[i-2][ (j+1) % resolution],
						points[i-2][j], 
						top
					));
				} else {
					tris.addAll( Arrays.asList( new Quad( 
						points[i-1][j], 
						points[i-1][ (j+1) % resolution], 
						points[i-2][ (j+1) % resolution],
						points[i-2][j]
					).trianglify() ));
				}
			}
		}
		
		return new Entity(new Mesh(tris, null));
	}
	
	public static IEntity createSphere( double diameter, int resolution, Vector3 position, Vector3 direction ) {
		return createSphere( diameter, resolution, position, direction, Color.BLUE );
	}
	
}
