package renderer.raycast;

import renderer.math.Vector3;
import renderer.primitives.Triangle;

public class IntersectResult {

	public Vector3 position = null;
	public Vector3 normal = null;
	public Object shape = null;
	
	public IntersectResult() { }
	
	public IntersectResult(Vector3 position, Vector3 normal, Triangle triangle) {
		this.position = position;
		this.normal = normal;
		this.shape = triangle;
	}
	
	@Override
	public String toString() {
		return String.format(
			"IntersectResult (%s, %s, %s)",
			this.position.toString(),
			this.normal.toString(),
			this.shape.toString()
		);
	}
	
}