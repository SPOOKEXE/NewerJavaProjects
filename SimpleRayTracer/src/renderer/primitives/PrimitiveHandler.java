package renderer.primitives;

import java.util.ArrayList;

import renderer.lib.HitResult;
import renderer.lib.Ray;
import renderer.math.Vector3;

public class PrimitiveHandler {

	static Vector3[] SQUARE_CORNERS_VECTORS = new Vector3[] {
		new Vector3(1, -1, 1),
		new Vector3(-1, -1, 1),
		new Vector3(1, -1, -1),
		new Vector3(-1, -1, -1),
		new Vector3(1, 1, 1),
		new Vector3(-1, 1, 1),
		new Vector3(1, 1, -1),
		new Vector3(-1, 1, -1),
	};
	
	// Fields //
	public ArrayList<BasePrimitive> primitives;
	
	// Constructors // 
	public PrimitiveHandler() {
		this.primitives = new ArrayList<BasePrimitive>();
	}
	
	// Methods //
	public void clear() {
		this.primitives.clear();
	}
	
	public void add( BasePrimitive prim ) {
		this.primitives.add(prim);
	}
	
	public HitResult FindClosestIntersect(Ray ray, float t_min, float t_max) {
		HitResult closest = null;
	    float closest_max = t_max;

	    for (BasePrimitive object : this.primitives) {
//	    	if (!object.IsRayIntersecting( ray )) {
//	    		continue;
//	    	}
	    	
	    	HitResult result = object.RayIntersect(ray, t_min, closest_max);
	        if (result != null) {
	        	closest_max = result.t;
	        	closest = result;
	        }
	    }
	    
	    return closest;
	}
	
}
