package renderer.lib;

import renderer.material.BaseMaterial;

public abstract class BasePrimitive {

	// Fields //
	public BaseMaterial material;
	
	// Constructors //
	public BasePrimitive() {}
	
	// Methods //
	public HitResult RayIntersect( Ray ray, float t_min, float t_max ) {
		return null;
	}

}
