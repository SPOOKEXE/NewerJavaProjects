package renderer.lib;

import renderer.material.BaseMaterial;
import renderer.math.Vector3;

public abstract class BasePrimitive {

	// Fields //
	public Vector3 position;
	public BaseMaterial material;
	
	// Constructors //
	public BasePrimitive() {}
	
	// Methods //
	public boolean IntersectsBounds( Ray ray ) {
		return false;
	}
	
	public HitResult RayIntersect( Ray ray, float t_min, float t_max ) {
		return null;
	}

}
