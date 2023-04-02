package renderer.lib;

import renderer.material.BaseMaterial;
import renderer.math.Vector3;

public class Sphere extends BasePrimitive {

	// Fields //
	public float radius;
	
	// Constructors //
	public Sphere() { }
	public Sphere(Vector3 position, float radius) {
		this.position = position;
		this.radius = radius;
	}
	public Sphere(Vector3 position, float radius, BaseMaterial material) {
		this.position = position;
		this.radius = radius;
		this.material = material;
	}
	
	// Methods //
	@Override
	public boolean IntersectsBounds( Ray ray ) {
		return true;
	}
	
	
	@Override
	public HitResult RayIntersect( Ray ray, float t_min, float t_max ) {
		
		Vector3 oc = ray.origin.sub( this.position );

		float a = ray.direction.mag_sqred();
		float half_b = oc.dot(ray.direction);
		float c = oc.mag_sqred() - (this.radius*this.radius);
		
		float discriminant = (half_b*half_b) - (a*c);
		if (discriminant < 0.0f) {
			return null;
		}
		
	    float sqrtd = (float) Math.sqrt(discriminant);

	    // Find the nearest root that lies in the acceptable range.
	    float root = (-half_b - sqrtd) / a;
	    if (root < t_min || t_max < root) {
	        root = (-half_b + sqrtd) / a;
	        if (root < t_min || t_max < root) {
	        	return null;
	        }
	    }

	    Vector3 position = ray.towardsDirection(root);
	    Vector3 outward_normal = ( position.sub(this.position) ).div(this.radius);

	    HitResult result = new HitResult(position, null, root);
	    result.setFaceNormal(ray, outward_normal);
	    result.material = this.material;
	    return result;
	}
	
}
