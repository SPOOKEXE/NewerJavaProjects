package renderer.primitives;

import renderer.boundbox.BoundingBox;
import renderer.lib.HitResult;
import renderer.lib.Ray;
import renderer.material.BaseMaterial;
import renderer.math.Vector3;

public abstract class BasePrimitive {

	// Fields //
	public Vector3 position;
	public BaseMaterial material;
	public BoundingBox bounds;
	
	// Constructors //
	public BasePrimitive() {
		this.bounds = new BoundingBox();
	}
	
	// Methods //
	public void GenerateBoundingBox() {
		
	}

	public boolean IsRayIntersecting( Ray ray ) {
		return this.bounds.IsRayIntersecting(ray);
	}
	
	public HitResult RayIntersect( Ray ray, float t_min, float t_max ) {
		return null;
	}

}
