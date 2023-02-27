package renderer.math;

public class Vector2 {

	public double x, y;

	//OBJECT-BASED //
	public Vector2( double x, double y ) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2() {
		this.x = this.y; // x, y, z = 0, starting from z backwards to x.
	}
	
	// OBJECT METHODS //
	public Vector2 add( Vector2 other ) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2 sub( Vector2 other ) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vector2 mult( Vector2 other ) {
		this.x *= other.x;
		this.y *= other.y;
		return this;
	}
	
	// multiply with scalar number
	public Vector2 mult( Integer multiply ) {
		this.x *= multiply;
		this.y *= multiply;
		return this;
	}
	
	public Vector2 mult( double multiply ) {
		this.x *= multiply;
		this.y *= multiply;
		return this;
	}
	
	public Vector2 mult( float multiply ) {
		return this.mult( (double) multiply );
	}
	
	// distance to other vector
	public double dist( Vector2 other ) {
		return Math.sqrt(
			Math.pow(this.x - other.x, 2) +
			Math.pow(this.y - other.y, 2)
		);
	}
	
	// normalize to a magnitude of 1
	public Vector2 normalize() {
		double magnitude = Math.sqrt( (this.x * this.x) + (this.y * this.y) );
		this.x = this.x / magnitude;
		this.y = this.y / magnitude;
		return this;
	}
	
	// STATIC METHODS //
	public static double dist( Vector2 p1, Vector2 p2 ) {
		return Math.sqrt( Math.pow( p1.x - p2.x, 2 ) + Math.pow( p1.y - p2.y, 2 ) );
	}
	
	// Gives the scalar product of the two lines (multiplying two vectors together)
	// "are the product of the Euclidean magnitudes of the two vectors and the cosine of the angle between them"
	public static double dot( Vector2 v1, Vector2 v2 ) {
		return (v1.x * v2.x) + (v1.y * v2.y); 
	}
	
	public static Vector2 normalize( Vector2 v ) {
		double magnitude = Math.sqrt( (v.x * v.x) + (v.y * v.y) );
		return new Vector2( (v.x / magnitude), (v.y / magnitude) );
	}
	
	@Override
	public String toString() {
		return this.x + ", " + this.y;
	}
	
}
