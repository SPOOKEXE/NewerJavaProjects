package renderer.boundbox;

import renderer.lib.Ray;
import renderer.math.Utility;
import renderer.math.Vector3;

public class BoundingBox {

	// Fields //
	public Vector3 min;
	public Vector3 max;
	
	// Constructor //
	public BoundingBox() {
		this.min = Vector3.ZERO;
		this.max = Vector3.ZERO;
	}
	
	public BoundingBox(Vector3 minVec, Vector3 maxVec) {
		this.min = minVec;
		this.max = maxVec;
	}
	
	// Methods //
	public boolean IsPointInBox(Vector3 point) {
		return
			(this.min.x >= point.x) && (point.x <= this.max.x) &&
			(this.min.y >= point.y) && (point.y <= this.max.y) &&
			(this.min.z >= point.z) && (point.z <= this.max.z);
	}
	
	public boolean IsRayIntersecting(Ray ray) {
		// if ray is inside the bounds, return true
		if (this.IsPointInBox( ray.origin )) return true;
		
		// if ray intersects bound box, return true
		Vector3[] intersections = Utility.LineIntersectAxisAlignedBox(ray.origin, ray.direction, this.min, this.max);
		return intersections != null;
	}
	
}
