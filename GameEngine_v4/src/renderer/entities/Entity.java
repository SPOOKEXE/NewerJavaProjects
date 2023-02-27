package renderer.entities;

import java.util.ArrayList;
import java.util.List;

import renderer.geometry.BoundingBox;
import renderer.geometry.Mesh;
import renderer.geometry.Triangle;
import renderer.material.BaseMaterial;
import renderer.math.CFrame;
import renderer.math.Vector3;

public class Entity implements IEntity {
	public String name;
	
	public CFrame cframe;
	public Vector3 size;
	public List<Mesh> meshes;
	public BoundingBox bounds;
	
	public Entity( List<Mesh> meshes ) {
		this.meshes = meshes;
		this.bounds = BoundingBox.GetBoundingBoxForMeshes(this.meshes);
	}
	
	public Entity( Mesh mesh ) {
		this.meshes = new ArrayList<Mesh>();
		this.meshes.add(mesh);
		this.bounds = BoundingBox.GetBoundingBoxForMeshes(this.meshes);
	}
	
	public Entity( List<Mesh> meshes, String name  ) {
		this.name = name;
		this.meshes = meshes;
		this.bounds = BoundingBox.GetBoundingBoxForMeshes(this.meshes);
	}
	
	public Entity( Mesh mesh, String name ) {
		this.name = name;
		this.meshes = new ArrayList<Mesh>();
		this.meshes.add(mesh);
		this.bounds = BoundingBox.GetBoundingBoxForMeshes(this.meshes);
	}
	
	@Override
	public List<Triangle> getTriangles() {
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (Mesh mesh : this.meshes) {
			triangles.addAll(mesh.triangles);
		}
		return triangles;
	}
	
	@Override
	public void setSize(Vector3 size) {
		this.size = size;
	}
	
	@Override
	public void setPosition(Vector3 position) {
		Vector3 dir = this.cframe.LookVector;
		this.cframe = new CFrame(position, position.add(dir));
	}
	
	@Override
	public void setMeshes(List<Mesh> meshes) {
		this.meshes = meshes;
		this.bounds = BoundingBox.GetBoundingBoxForMeshes(this.meshes);
	}
	
	@Override
	public void appendMeshes(List<Mesh> meshes) {
		this.meshes.addAll(meshes);
		this.bounds = BoundingBox.GetBoundingBoxForMeshes(this.meshes);
	}
	
	@Override
	public List<Mesh> getMeshes() {
		return this.meshes;
	}

	@Override
	public CFrame getCFrame() {
		return this.cframe;
	}

	@Override
	public Vector3 getPosition() {
		return this.cframe.Position;
	}

	@Override
	public Vector3 getLookVector() {
		return this.cframe.LookVector;
	}

	@Override
	public Vector3 getRightVector() {
		return this.cframe.RightVector;
	}

	@Override
	public Vector3 getUpVector() {
		return this.cframe.UpVector;
	}

	@Override
	public void setCFrame(CFrame cf) {
		this.cframe = cf;
	}
	
	@Override
	public String toString() {
		return this.name;
//		StringBuilder meshData = new StringBuilder();
//		for (Mesh mesh : this.meshes) {
//			meshData.append(mesh + ", ");
//		}
//		return String.format("%s | Position %s | Triangles %s", this.name, this.cframe.Position, this.getTriangles().size());
	}

	@Override
	public void setMaterial(BaseMaterial material) {
		for (Triangle tri : this.getTriangles()) {
			tri.setMaterial(material);
		}
	}

	@Override
	public BoundingBox getBounds() {
		return this.bounds;
	}

}
