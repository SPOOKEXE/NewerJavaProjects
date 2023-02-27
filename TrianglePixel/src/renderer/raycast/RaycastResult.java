package renderer.raycast;

import renderer.math.Vector3;
import renderer.primitives.Triangle;

public class RaycastResult {
	// public Material Material;
	public renderer.primitives.Mesh Mesh = null;
	public Vector3 Position = null;
	public Vector3 Normal = null;
	public Triangle Triangle = null;

	public RaycastResult() {
		
	}
	
	public RaycastResult(renderer.primitives.Mesh mesh, Vector3 position, Vector3 normal, Triangle triangle) {
		this.Mesh = mesh;
		this.Position = position;
		this.Normal = normal;
		this.Triangle = triangle;
	}
}