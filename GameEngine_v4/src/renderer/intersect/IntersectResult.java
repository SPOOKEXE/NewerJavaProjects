package renderer.intersect;

import renderer.math.Vector3;

public class IntersectResult {

	public Vector3 position = null;
	public Vector3 normal = null;
	public Object data = null;
	
	public IntersectResult() { }
	
	public IntersectResult(Vector3 position, Vector3 normal, Object data) {
		this.position = position;
		this.normal = normal;
		this.data = data;
	}
	
	@Override
	public String toString() {
		return String.format(
			"IntersectResult (%s, %s, %s)",
			this.position.toString(),
			this.normal.toString(),
			this.data.toString()
		);
	}
	
}
