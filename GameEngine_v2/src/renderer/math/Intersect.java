package renderer.math;

public class Intersect {

	// https://tutorial.math.lamar.edu/Classes/CalcIII/EqnsOfPlanes.aspx
	public static Vector3 GetTrianglePlaneNormal( Vector3 p1, Vector3 p2, Vector3 p3 ) {
		return p2.copy().sub(p1).cross( p3.copy().sub(p1) ).normalize();
	}
	
	public static Vector3 LinePlaneIntersect( Vector3 seg0, Vector3 seg1, Vector3 point_on_plane, Vector3 plane_normal ) {
		Vector3 u = seg1.copy().sub(seg0);
		double dot = Vector3.dot(plane_normal.copy(), u);
		if (Math.abs(dot) > 1e-6) {
			Vector3 w = seg0.copy().sub(point_on_plane);
			double fac = Vector3.dot(plane_normal.copy().mult(-1), w) / dot;
			u.mult(fac);
			return seg0.add(u);
		}
		return null;
	}
	
	// https://stackoverflow.com/questions/5666222/3d-line-plane-intersection
	public static boolean IsPointInTriangle( Vector3 a, Vector3 b, Vector3 c, Vector3 s ) {
		// System.out.println(String.format("(%s), (%s), (%s), (%s)", a, b, c, s));
		double as_x = s.x - a.x;
		double as_y = s.y - a.y;
		boolean s_ab = ((b.x-a.x) * as_y - (b.y-a.y) * as_x) > 0;
		if (((c.x-a.x) * as_y - (c.y-a.y) * as_x > 0) == s_ab) {
			return false;
		}
		if (((c.x-b.x) * (s.y-b.y) - (c.y-b.y) * (s.x-b.x) > 0) != s_ab) {
			return false;
		}
		return true;
	}
	
}
