package renderer.raycast;

import renderer.math.Vector3;
import renderer.primitives.Triangle;
import renderer.entity.IEntity;

public class RaycastResult {
	// public Material Material;
	public IEntity Entity = null;
	public Vector3 Position = null;
	public Vector3 Normal = null;
	public Triangle Triangle = null;

	public RaycastResult() {
		
	}
	
	public RaycastResult(IEntity entity, Vector3 position, Vector3 normal, Triangle triangle) {
		this.Entity = entity;
		this.Position = position;
		this.Normal = normal;
		this.Triangle = triangle;
	}
}