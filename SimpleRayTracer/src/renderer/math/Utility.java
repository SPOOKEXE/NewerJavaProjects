package renderer.math;

public class Utility {

	public static float INFINITY = Float.POSITIVE_INFINITY;
	public static float PI = (float) Math.PI;
	
	public static float degrees_to_radians(float degrees) {
		return degrees * Utility.PI / 180.0f;
	}
	
	public static float random_float() {
		return (float) Math.random();
	}
	
	public static float random_float(float min, float max) {
		return min + (max-min) * random_float();
	}
	
	public static Vector3 random_in_unit_sphere() {
		while (true) {
	        Vector3 p = Vector3.fromRandom(-1, 1);
	        if (p.mag_sqred() >= 1) continue;
	        return p;
	    }
	}
	
	public static Vector3 random_unit_vector() {
		return Utility.random_in_unit_sphere().unit();
	}
	
	public static Vector3 random_in_hemisphere( Vector3 normal ) {
		Vector3 in_unit_sphere = random_in_unit_sphere();
	    if (in_unit_sphere.dot(normal) > 0.0) {
	    	return in_unit_sphere;
	    }
	    return in_unit_sphere.mult(-1);
	}
	
	public static Vector3 random_in_unit_disk() {
		 while (true) {
	        Vector3 p = new Vector3(Utility.random_float(-1, 1), Utility.random_float(-1, 1), 0);
	        if (p.mag_sqred() >= 1) continue;
	        return p;
	    }
	}
	
	public static float clamp(float x, float min, float max) {
	    if (x < min) return min;
	    if (x > max) return max;
	    return x;
	}
	
	public static Vector3 randomColorVec() {
		return new Vector3( Utility.random_float(), Utility.random_float(), Utility.random_float() );
	}
	
	public static Vector3 randomColorVec(float min, float max) {
		return new Vector3( Utility.random_float(min, max), Utility.random_float(min, max), Utility.random_float(min, max) );
	}
	
	public static float sumFloats(float[] floatz) {
    	float sum = 0f;
    	for (int i = 0; i < floatz.length; i++) {
    		sum += floatz[i];
    	}
    	return sum;
    }
	
	public static boolean IsPointInSphere( Vector3 point, Vector3 center, float radius ) {
		float xc = center.x - point.x;
		float yc = center.y - point.y;
		float zc = center.z - point.z;
		return (xc*xc + yc*yc + zc*zc) < (radius * radius);
	}
	
	public static Vector3[] LineIntersectAxisAlignedBox( Vector3 rayOrigin, Vector3 rayDirection, Vector3 boxMin, Vector3 boxMax ) {
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

}
