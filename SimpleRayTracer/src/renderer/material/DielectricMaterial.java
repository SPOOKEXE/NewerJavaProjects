package renderer.material;

import renderer.lib.HitResult;
import renderer.lib.Ray;
import renderer.math.Utility;
import renderer.math.Vector3;

public class DielectricMaterial extends BaseMaterial {

	// Fields //
	float indexOfRefraction = 1f;

	// Constructors //
	public DielectricMaterial() { }
	public DielectricMaterial(float ir) {
		this.indexOfRefraction = ir;
	}
	
	// Methods //
	static float reflectance(float cosine, float ref_idx) {
        // Use Schlick's approximation for reflectance.
        float r0 = (1f-ref_idx) / (1f+ref_idx);
        r0 = r0*r0;
        return (float) (r0 + (1f-r0) * Math.pow((1f - cosine), 5f));
    }
	
	public MaterialReflectionData scatter( Ray r_in, HitResult hit ) {
		float refraction_ratio = hit.front_face ? (1.0f/this.indexOfRefraction) : this.indexOfRefraction;
		
		Vector3 unit_direction = r_in.direction.unit();
		float cos_theta = Math.min(unit_direction.mult(-1f).dot(hit.normal), 1.0f);
		float sin_theta = (float) Math.sqrt(1.0f - (cos_theta*cos_theta));
		
		boolean cannot_refract = (refraction_ratio * sin_theta) > 1.0f;
		Vector3 direction;
		if (cannot_refract || DielectricMaterial.reflectance(cos_theta, refraction_ratio) > Utility.random_float()) {
            direction = Vector3.reflect(unit_direction, hit.normal);
		} else {
            direction = Vector3.refract(unit_direction, hit.normal, refraction_ratio);
		}
		
        MaterialReflectionData material_dat = new MaterialReflectionData();
        material_dat.scatter_direction = direction;
        material_dat.attenuation = new Vector3(1.0f, 1.0f, 1.0f);
        material_dat.scattered = new Ray(hit.position, direction);
        return material_dat;
	}
	
}
