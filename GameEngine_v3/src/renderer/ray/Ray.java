package renderer.ray;

import renderer.math.Vector3;

public class Ray {

	// Fields //
	Vector3 position;
	Vector3 direction;
	
	// Constructors //
	public Ray() {
		this.position = Vector3.ZERO;
		this.direction = Vector3.ZERO;
	}
	
	public Ray(Vector3 position, Vector3 direction) {
		this.position = position;
		this.direction = direction;
	}
	
	// Methods //
	
}
