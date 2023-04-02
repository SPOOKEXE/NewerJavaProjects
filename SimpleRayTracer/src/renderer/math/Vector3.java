package renderer.math;

// https://github.com/EgoMooseOldProjects/Vector3-and-CFrame/blob/master/CFrame%20and%20Vector3/CFrame%20and%20Vector3/Vector3.cs

public class Vector3 {
	
	public final static Vector3 ZERO = new Vector3(0, 0, 0);
	public final static Vector3 NAN = new Vector3(Float.NaN, Float.NaN, Float.NaN);

	public final static Vector3 POSITIVE_INFINITY = new Vector3(
		Float.POSITIVE_INFINITY,
		Float.POSITIVE_INFINITY,
		Float.POSITIVE_INFINITY
	);
	
	public final static Vector3 NEGATIVE_INFINITY = new Vector3(
		Float.NEGATIVE_INFINITY,
		Float.NEGATIVE_INFINITY,
		Float.NEGATIVE_INFINITY
	);
	
	public float x;
	public float y;
	public float z;

	public Vector3(float x, float y, float z) {
		this.set(x, y, z);
	}
	
	public Vector3(double x, double y, double z) {
		this.set((float)x, (float)y, (float)z);
	}

	public Vector3(Vector3 v) {
		this.set(v);
	}

	public Vector3() {
		this.set(0f, 0f, 0f);
	}

	public Vector3 set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vector3 set(Vector3 v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
	}
	
	public Vector3 unit() {
		float norm = this.mag();
		if (norm == 0) {
			return new Vector3();
		}
		return new Vector3(this.x / norm, this.y / norm, this.z / norm);
	}
	
	public float mag() {
		return (float)Math.sqrt(this.dot(this));
	}
	
	public float mag_sqred() {
		float m = this.mag();
		return m * m;
	}

	public Vector3 add(Vector3 other) {
		return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
	}
	
	public Vector3 add(float a) {
		return new Vector3(this.x + a, this.y + a, this.z + a);
	}

	public Vector3 sub(float a) {
		return new Vector3(this.x - a, this.y - a, this.z - a);
	}
	
	public Vector3 sub(Vector3 other) {
		return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
	}

	public Vector3 div(Vector3 other) {
		return new Vector3(this.x / other.x, this.y / other.y, this.z / other.z);
	}
	
	public Vector3 div(float a) {
		return new Vector3(this.x / a, this.y / a, this.z / a);
	}
	
	public Vector3 mult(float a) {
		return new Vector3(this.x * a, this.y * a, this.z * a);
	}
	
	public Vector3 mult(Vector3 a) {
		return new Vector3(this.x * a.x, this.y * a.y, this.z * a.z);
	}
	
	public Vector3 cross(Vector3 other) {
		return new Vector3(
			this.y * other.z - other.y * this.z,
			this.z * other.x - other.z * this.x,
			this.x * other.y - other.x * this.y
		);
	}
	
	public float dot(Vector3 other) {
		return (this.x * other.x) + (this.y * other.y) + (this.z * other.z);
	}
	
	public Vector3 lerp(Vector3 other, float t) {
		return this.mult((1-t)).add(other.mult(t));
	}
	
	public Vector3 lerp(Vector3 other, double t) {
		return this.lerp(other, (float)t);
	}
	
	public boolean near_zero() {
		double s = 1e-8;
		 return (this.x < s) && (this.y < s) && (this.z < s);
	}
	
	public String toString() {
		return String.format("v3(%f,%f,%f)", this.x, this.y, this.z);
	}
	
	public static Vector3 fromRandom() {
		return new Vector3( Utility.random_float(), Utility.random_float(), Utility.random_float() );
	}
	
	public static Vector3 fromRandom(float min, float max) {
		return new Vector3( Utility.random_float(min, max), Utility.random_float(min, max), Utility.random_float(min, max) );
	}
	
	public static Vector3 reflect(Vector3 vec, Vector3 normal) {
		return vec.sub( normal.mult( vec.dot(normal) * 2.0f) );
	}
	
	public static Vector3 refract(Vector3 uv, Vector3 n, float etai_over_etat) {
		float cos_theta = Math.min(uv.mult(-1).dot(n), 1.0f);
	    Vector3 r_out_perp = uv.add(n.mult(cos_theta)).mult(etai_over_etat);
	    float parallel_n = (float) -Math.sqrt(Math.abs(1.0f - r_out_perp.mag_sqred()));
	    Vector3 r_out_parallel = n.mult(parallel_n);
	    return r_out_perp.add(r_out_parallel);
	}
	
}