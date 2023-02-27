package renderer.point;

public class Point3D {

	public double x, y, z;
	
	public Point3D() {
		this.x = this.y = this.z = 0;
	}
	
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// STATIC CLASS METHODS //
	public static double dist( Point3D p1, Point3D p2 ) {
		double x2 = Math.pow( p1.x - p2.x, 2 );
		double y2 = Math.pow( p1.y - p2.y, 2 );
		double z2 = Math.pow( p1.z - p2.z, 2 );
		return Math.sqrt( x2 + y2 + z2 );
	}
	
	@Override
	public String toString() {
		return this.x + ", " + this.y + ", " + this.z;
	}
	
}
