package renderer.lib;

import renderer.math.Vector3;

public class Ray {

	// Fields //
	public Vector3 origin;
	public Vector3 direction;

	// Constructors //
	public Ray() {
		this.origin = new Vector3();
		this.direction = new Vector3(1, 0, 0);
	}
	
	public Ray(Vector3 origin, Vector3 direction) {
		this.origin = origin;
		this.direction = direction;
	}
	
	// Methods //
	public Vector3 towardsDirection(float dist) {
		return this.origin.add( this.direction.mult(dist) );
	}
	
}
