package renderer.intersect;

import renderer.camera.ICamera;
import renderer.debug.RenderingDebugOutput;
import renderer.geometry.Quad;
import renderer.geometry.Triangle;
import renderer.math.CFrame;
import renderer.math.Vector3;

public class IntersectManager {

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
	
	// https://web.archive.org/web/20170517125238/http://www.cs.virginia.edu/~gfx/courses/2003/ImageSynthesis/papers/Acceleration/Fast%20MinimumStorage%20RayTriangle%20Intersection.pdf
	public static IntersectResult LineTriangleIntersect( Triangle triangle, Vector3 position, Vector3 direction ) {
		Vector3 seg0 = position;
		Vector3 seg1 = position.add( direction.unit().mult(500) );
		
		Vector3 p1 = triangle.v1.toVector3();
		Vector3 p2 = triangle.v2.toVector3();
		Vector3 p3 = triangle.v3.toVector3();
		
		Vector3 plane_normal = GetTrianglePlaneNormal( p1, p2, p3 );
		Vector3 planeIntersectPoint = LinePlaneIntersect( seg0, seg1, p1, plane_normal );
		
		if (planeIntersectPoint == null) {
			// System.out.println("no plane intersection");
			RenderingDebugOutput.DumpLineTriangleIntersect(triangle, position, direction, false);
			return null;
		}
		
		if (!IsPointInTriangle(p1, p2, p3, planeIntersectPoint)) {
			// System.out.println("point is not within triangle");
			RenderingDebugOutput.DumpLineTriangleIntersect(triangle, position, direction, false);
			return null;
		}
		
		RenderingDebugOutput.DumpLineTriangleIntersect(triangle, position, direction, true);
		System.out.println("point intersects triangle");
		return new IntersectResult(planeIntersectPoint, plane_normal, triangle);
	}
	
	// Break into triangles then parse through triangle function
	public static IntersectResult LineQuadIntersect( Quad quad, Vector3 position, Vector3 direction ) {
		Triangle[] tris = quad.trianglify(); // results in 2 triangles
		
		// Try first triangle part
		IntersectResult intersect0 = LineTriangleIntersect( tris[0], position, direction );
		intersect0.data = quad;
		if (intersect0 != null) {
			return intersect0; // first triangle succeeded
		}
		
		// Try second triangle part
		@SuppressWarnings("unused") // its not dead code if the first statement fails :shrug:
		IntersectResult intersect1 = LineTriangleIntersect( tris[1], position, direction );
		intersect1.data = quad;
		if (intersect1 != null) {
			return intersect1; // second triangle succeeded
		}
		
		// No success
		return null;
	}
	
	// https://tutorial.math.lamar.edu/Classes/CalcIII/EqnsOfPlanes.aspx
	public static Vector3 GetTrianglePlaneNormal( Vector3 p1, Vector3 p2, Vector3 p3 ) {
		return p2.sub(p1).cross( p3.sub(p1) ).unit();
	}
	
	public static Vector3 LinePlaneIntersect( Vector3 seg0, Vector3 seg1, Vector3 point_on_plane, Vector3 plane_normal ) {
		Vector3 u = seg1.sub(seg0);
		double dot = plane_normal.dot(u);
		if (Math.abs(dot) > 1e-6) {
			Vector3 w = seg0.sub(point_on_plane);
			double fac = plane_normal.mult(-1).dot(w) / dot;
			u.mult((float) fac);
			return seg0.add(u);
		}
		return null;
	}

	// https://stackoverflow.com/questions/3106666/intersection-of-line-segment-with-axis-aligned-box-in-c-sharp#3115514
	public static Vector3[] LineIntersectAxisAlignedBox( Vector3 rayOrigin, Vector3 rayDirection, Vector3 boxMin, Vector3 boxMax ) {
		RenderingDebugOutput.DumpBoxIntersectCheck(rayOrigin, rayDirection, boxMin, boxMax);
		
		Vector3 min = boxMin.sub(rayOrigin);
		Vector3 max = boxMax.sub(rayOrigin);
		float near = Float.MIN_VALUE;
		float far = Float.MAX_VALUE;
		
		// X
		float t1 = min.x / rayDirection.x;
		float t2 = max.x / rayDirection.x;
		float tMin = Math.min(t1, t2);
		float tMax = Math.max(t1, t2);
		
		if (tMin > near) { near = tMin; }
		if (tMax < far) { far = tMax; }
		if (near > far || far < 0) {
			return null;
		}
	
		// Y
		t1 = min.y / rayDirection.y;
		t2 = max.y / rayDirection.y;
		tMin = Math.min(t1, t2);
		tMax = Math.max(t1, t2);
		if (tMin > near) { near = tMin; }
		if (tMax < far) { far = tMax; }
		if (near > far || far < 0) {
			return null;
		}
		
		// Z
		t1 = min.z / rayDirection.z;
		t2 = max.z / rayDirection.z;
		tMin = Math.min(t1, t2);
		tMax = Math.max(t1, t2);
		if (tMin > near) { near = tMin; }
		if (tMax < far) { far = tMax; }
		if (near > far || far < 0) {
			return null;
		}

		return new Vector3[] { rayOrigin.add(rayDirection.mult(near)), rayOrigin.add(rayDirection.mult(far)) };
	}
	
	// Check if a point is within the camera
	public static boolean IsPointWithinCameraFOV(ICamera camera, Vector3 point) {
		CFrame cameraCFrame = camera.getCFrame();
		Vector3 delta = point.sub(cameraCFrame.Position);
		return delta.unit().dot(cameraCFrame.LookVector) > 0;
	}
	
	// Check if bounding box is within camera
	public static boolean IsBoxWithinCameraFOV(ICamera camera, Vector3 min, Vector3 max) {
		if (!IsPointWithinCameraFOV(camera, min)) { return false; }
		if (!IsPointWithinCameraFOV(camera, max)) { return false; }
		return true;
	}
	
}
