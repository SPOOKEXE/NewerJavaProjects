package renderer.entity;

import java.util.ArrayList;
import java.util.List;

import renderer.primitives.Mesh;
import renderer.primitives.Triangle;

public class Entity implements IEntity {

	public List<Mesh> meshes;
	
	public Entity( List<Mesh> mesh ) {
		this.meshes = new ArrayList<Mesh>();
		this.meshes.addAll(mesh);
	}
	
	public Entity( Mesh mesh ) {
		this.meshes = new ArrayList<Mesh>();
		this.meshes.add(mesh);
	}
	
	public List<Triangle> getTriangles() {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (Mesh mesh : this.meshes) {
			triangles.addAll(mesh.triangles);
		}
		return triangles;	
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public String toString() {
		return String.format(
			"Entity | %s | %s | Triangle Count: %s",
			Integer.toHexString(this.hashCode()),
			this.meshes.get(0).triangles.get(0).toString(),
			this.meshes.size()
		);
	}
	
}
