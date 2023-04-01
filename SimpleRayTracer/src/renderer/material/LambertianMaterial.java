package renderer.material;

import renderer.lib.HitResult;
import renderer.lib.Ray;
import renderer.math.Utility;
import renderer.math.Vector3;

public class LambertianMaterial extends BaseMaterial {

	// Fields //
	Vector3 albedo;
	
	// Constructors //
	public LambertianMaterial(Vector3 a) {
		this.albedo = a;
	}
	
	// Methods //
	public MaterialReflectionData scatter( Ray r_in, HitResult hit ) {
		MaterialReflectionData material_dat = new MaterialReflectionData();
		material_dat.scatter_direction = hit.normal.add( Utility.random_unit_vector() );

		if (material_dat.scatter_direction.near_zero())
			material_dat.scatter_direction = hit.normal;

		material_dat.scattered = new Ray(hit.position, material_dat.scatter_direction);
		material_dat.attenuation = this.albedo;
        return material_dat;
	}
	
}
