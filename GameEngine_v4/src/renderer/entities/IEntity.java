package renderer.entities;

import java.util.List;

import renderer.geometry.BoundingBox;
import renderer.geometry.Mesh;
import renderer.geometry.Triangle;
import renderer.material.BaseMaterial;
import renderer.math.CFrame;
import renderer.math.Vector3;

public interface IEntity {
	// GETS
	List<Triangle> getTriangles();
	List<Mesh> getMeshes();
	CFrame getCFrame();
	Vector3 getPosition();
	Vector3 getLookVector();
	Vector3 getRightVector();
	Vector3 getUpVector();
	BoundingBox getBounds();
	// SETS + APPENDS
	void appendMeshes(List<Mesh> meshes);
	void setMeshes(List<Mesh> meshes);
	void setCFrame(CFrame cf);
	void setPosition(Vector3 position);
	void setSize(Vector3 size);
	void setMaterial(BaseMaterial material);
	// MISC
	String toString();
}
