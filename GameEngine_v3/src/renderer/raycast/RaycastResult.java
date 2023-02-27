package renderer.raycast;

import renderer.entities.IEntity;
import renderer.geometry.Triangle;
import renderer.math.Vector3;

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
	
	@Override
	public String toString() {
		return String.format("RaycastResult(%s,%s,%s,%s)", this.Entity, this.Position, this.Normal, this.Triangle);
	}
}