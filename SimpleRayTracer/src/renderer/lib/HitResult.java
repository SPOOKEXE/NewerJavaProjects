package renderer.lib;

import renderer.material.BaseMaterial;
import renderer.math.Vector3;

public class HitResult {

	// Fields //
	public Vector3 position;
	public Vector3 normal;
	public BaseMaterial material;
	public float t;
	public boolean front_face;
	
	// Constructors //
	public HitResult() { }
	public HitResult(Vector3 position, Vector3 normal, float t) {
		this.position = position;
		this.normal = normal;
		this.t = t;
	}
	
	// Methods //
	public void setFaceNormal(Ray ray, Vector3 outward_normal) {
		this.front_face = outward_normal.dot(ray.direction) < 0;
		this.normal = this.front_face ? outward_normal : outward_normal.mult(-1f);
	}
	
}
