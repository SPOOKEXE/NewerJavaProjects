package renderer.material;

import renderer.lib.HitResult;
import renderer.lib.Ray;
import renderer.math.Utility;
import renderer.math.Vector3;

public class MetalMaterial extends BaseMaterial {

	// Fields //
	Vector3 albedo;
	float fuzz = 0;
	
	// Constructors //
	public MetalMaterial(Vector3 a, float fuzz) {
		this.albedo = a;
		this.fuzz = fuzz;
	}
	
	public MetalMaterial(Vector3 a) {
		this.albedo = a;
	}
	
	// Methods //
	public MaterialReflectionData scatter( Ray r_in, HitResult hit ) {
		Vector3 reflected = Vector3.reflect(r_in.direction.unit(), hit.normal);
		if (reflected.dot(hit.normal) <= 0) {
			return null;
		}
		
		MaterialReflectionData material_dat = new MaterialReflectionData();
		material_dat.scatter_direction = reflected;
		material_dat.scattered = new Ray(hit.position, reflected.add( Utility.random_in_unit_sphere().mult(this.fuzz) ));
		material_dat.attenuation = albedo;
        return material_dat;
	}
	
}
