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
		this.set(0, 0, 0);
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
		return new Vector3(this.x / norm, this.y / norm, this.z / norm);
	}
	
	public float mag() {
		return (float)Math.sqrt(this.dot(this));
	}

	public Vector3 add(Vector3 other) {
		return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
	}

	public Vector3 sub(Vector3 other) {
		return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
	}

	public Vector3 div(Vector3 other) {
		return new Vector3(this.x / other.x, this.y / other.y, this.z / other.z);
	}
	
	public Vector3 mult(float a) {
		return new Vector3(this.x * a, this.y * a, this.z * a);
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

	public String toString() {
		return String.format("v3(%s,%s,%s)", x, y, z);
	}
}