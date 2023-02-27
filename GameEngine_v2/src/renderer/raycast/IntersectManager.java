package renderer.raycast;

import renderer.math.Intersect;
import renderer.math.Vector3;
import renderer.primitives.Quad;
import renderer.primitives.Triangle;

public class IntersectManager {

	// https://web.archive.org/web/20170517125238/http://www.cs.virginia.edu/~gfx/courses/2003/ImageSynthesis/papers/Acceleration/Fast%20MinimumStorage%20RayTriangle%20Intersection.pdf
	public static IntersectResult LineTriangleIntersect( Triangle triangle, Vector3 position, Vector3 direction ) {
		Vector3 seg0 = position;
		Vector3 seg1 = position.copy().add( direction.copy().normalize().mult(5000) );
		
		Vector3 p1 = triangle.v1.toVector3();
		Vector3 p2 = triangle.v2.toVector3();
		Vector3 p3 = triangle.v3.toVector3();
		
		Vector3 plane_normal = Intersect.GetTrianglePlaneNormal( p1, p2, p3 );
		Vector3 planeIntersectPoint = Intersect.LinePlaneIntersect( seg0, seg1, p1, plane_normal );
		
		if (planeIntersectPoint == null) {
			return null;
		}
		
		if (!Intersect.IsPointInTriangle( p1, p2, p3, planeIntersectPoint )) {
			return null;
		}
		
		return new IntersectResult(planeIntersectPoint, plane_normal, triangle);
	}
	
	// Break into triangles then parse through triangle function
	public static IntersectResult LineQuadIntersect( Quad quad, Vector3 position, Vector3 direction ) {
		Triangle[] tris = quad.trianglify(); // results in 2 triangles
		
		// Try first triangle part
		IntersectResult intersect0 = LineTriangleIntersect( tris[0], position, direction );
		intersect0.shape = quad;
		if (intersect0 != null) {
			return intersect0; // first triangle succeeded
		}
		
		// Try second triangle part
		@SuppressWarnings("unused")
		IntersectResult intersect1 = LineTriangleIntersect( tris[1], position, direction );
		intersect1.shape = quad;
		if (intersect1 != null) {
			return intersect1; // second triangle succeeded
		}
		
		// No success
		return null;
	}
	
}
