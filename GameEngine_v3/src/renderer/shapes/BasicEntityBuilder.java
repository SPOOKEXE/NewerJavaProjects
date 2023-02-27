package renderer.shapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.entities.Entity;
import renderer.entities.IEntity;
import renderer.geometry.Mesh;
import renderer.geometry.Quad;
import renderer.geometry.Triangle;
import renderer.geometry.Vertex;
import renderer.material.BaseMaterial;
import renderer.math.CFrame;
import renderer.math.Vector3;

public class BasicEntityBuilder {
	
	private static IEntity createCube( float size, BaseMaterial material ) {
		float half_size = size / 2;
		
		Vertex v1 = new Vertex( -half_size, -half_size, -half_size );
		Vertex v2 = new Vertex( -half_size, +half_size, -half_size );
		Vertex v3 = new Vertex( -half_size, +half_size, +half_size );
		Vertex v4 = new Vertex( -half_size, -half_size, +half_size );
		
		Vertex v5 = new Vertex( +half_size, -half_size, -half_size );
		Vertex v6 = new Vertex( +half_size, +half_size, -half_size );
		Vertex v7 = new Vertex( +half_size, +half_size, +half_size );
		Vertex v8 = new Vertex( +half_size, -half_size, +half_size );
		
		Quad faceLeft = new Quad( v1, v2, v3, v4, material);
		Quad faceRight = new Quad( v5, v6, v7, v8, material);
		Quad faceFront = new Quad( v1, v2, v6, v5, material);
		Quad faceBack = new Quad( v4, v3, v7, v8, material);
		Quad faceUp = new Quad( v2, v3, v6, v7, material);
		Quad faceDown = new Quad( v1, v4, v5, v8, material);
		
		Mesh newMesh = new Mesh(faceLeft, faceRight, faceFront, faceBack, faceUp, faceDown);
		newMesh.setMaterial(material);
		return new Entity(newMesh, "CUBE");
	}
	
	public static IEntity createCube( float size, CFrame cf, BaseMaterial material) {
		IEntity cube = createCube(size, material);
		cube.setCFrame(cf);
		return cube;
	}
	
	public static IEntity createCube( float size, Vector3 position) {
		return createCube(size, new CFrame(position), new BaseMaterial(Color.BLUE));
	}
	
	public static IEntity createCube( float size, Vector3 position, Vector3 direction) {
		return createCube( size, new CFrame(position, direction), new BaseMaterial(Color.BLUE));
	}
	
	public static IEntity createSphere( float diameter, int resolution, BaseMaterial material ) {
	
		float radius = diameter / 2;
		
		List<Triangle> tris = new ArrayList<Triangle>();
		
		Vector3 bottom = new Vector3(0, 0, -radius);
		Vector3 top = new Vector3(0, 0, +radius);
		
		Vector3[][] points = new Vector3[resolution - 1][resolution];
		
		for (int i = 1; i < resolution; i++) {
			double theta = (Math.PI / resolution) * i;
			double zPos = -Math.cos(theta) * radius; //TODO: try change to sine and see what shape it makes
			double currentRadius = Math.abs( Math.sin(theta) * radius );
			for (int j = 0; j < resolution; j++) {
				double alpha = 2 * (Math.PI / resolution) * j;
				double xPos = -Math.sin(alpha) * currentRadius;
				double yPos = Math.cos(alpha) * currentRadius;
				points[i-1][j] = new Vector3(xPos, yPos, zPos);
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
		
		return new Entity(new Mesh(tris, null, material), "Sphere");
	}
	
	public static IEntity createSphere( float diameter, int resolution, CFrame cf, BaseMaterial material) {
		IEntity sphere = createSphere(diameter, resolution, material);
		sphere.setCFrame(cf);
		return sphere;
	}
	
	public static IEntity createSphere( float diameter, int resolution, Vector3 position) {
		return createSphere(diameter, resolution, new CFrame(position), new BaseMaterial(Color.RED));
	}
	
	public static IEntity createSphere( float diameter, int resolution, Vector3 position, Vector3 direction) {
		return createSphere( diameter, resolution, new CFrame(position, direction), new BaseMaterial(Color.RED));
	}
	
}
