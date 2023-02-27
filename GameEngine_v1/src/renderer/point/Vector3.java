package renderer.point;

public class Vector3 {

	public double x, y, z;

	//OBJECT-BASED //
	public Vector3( double x, double y, double z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3() {
		this.x = this.y = this.z = 0; // x, y, z = 0, starting from z backwards to x.
	}
	
	public Vector3( Point3D p1, Point3D p2 ) {
		this.x = p2.x - p1.x;
		this.y = p2.y - p1.y;
		this.z = p2.z - p1.z;
	}
	
	// STATIC CLASS METHODS //
	public static double dist( Vector3 p1, Vector3 p2 ) {
		return Math.sqrt( Math.pow( p1.x - p2.x, 2 ) + Math.pow( p1.y - p2.y, 2 ) + Math.pow( p1.z - p2.z, 2 ) );
	}
	
	// Gives the scalar product of the two lines (multiplying two vectors together)
	// "are the product of the Euclidean magnitudes of the two vectors and the cosine of the angle between them"
	public static double dot( Vector3 v1, Vector3 v2 ) {
		return (v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z); 
	}
	
	// get the right angle line out of the plane created by the two vectors (right angles to both vectors)
	// note: the area of the two vectors is ends up being the magnitude of the cross product parallel line
	// https://www.mathsisfun.com/algebra/vectors-cross-product.html
	public static Vector3 cross( Vector3 v1, Vector3 v2 ) {
		return new Vector3( 
			(v1.y * v2.z) - (v1.z * v2.y),
			(v1.z * v2.x) - (v1.x - v2.z),
			(v1.x * v2.y) - (v1.y - v2.x)
		);
				
	}
	
	public static Vector3 normalize( Vector3 v ) {
		double magnitude = Math.sqrt( (v.x * v.x) + (v.y * v.y) + (v.z * v.z) );
		return new Vector3( (v.x / magnitude), (v.y / magnitude), (v.z / magnitude) );
	}
	
	@Override
	public String toString() {
		return this.x + ", " + this.y + ", " + this.z;
	}
	
}
