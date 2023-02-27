package renderer.raycast;

import renderer.math.Vector3;

public class Raycast {
	public Vector3 origin;
	public Vector3 direction;
	public RaycastParams parameters = null;
	
	public Raycast(Vector3 origin, Vector3 direction, RaycastParams parameters) {
		this.origin = origin;
		this.direction = direction;
		this.parameters = parameters;
	}
	
	public Raycast(Vector3 origin, Vector3 direction) {
		this.origin = origin;
		this.direction = direction;
		this.parameters = new RaycastParams(); // default parameters
	}
	
	@Override
	public String toString() {
		return String.format("Raycast (%s, %s, %s)", this.origin, this.direction, this.parameters);
	}
}