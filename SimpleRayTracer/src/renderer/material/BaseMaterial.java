package renderer.material;

import renderer.lib.HitResult;
import renderer.lib.Ray;
import renderer.math.Vector3;

public abstract class BaseMaterial {

	// Fields //
	Vector3 albedo = new Vector3();
	
	// Constructors //
	public BaseMaterial() {
		
	}
	
	// Methods //
	public MaterialReflectionData scatter( Ray r_in, HitResult hit ) {
		MaterialReflectionData material_dat = new MaterialReflectionData();
		material_dat.scatter_direction = r_in.direction;
		material_dat.scattered = r_in;
		material_dat.attenuation = this.albedo;
		return material_dat;
	}
	
}
