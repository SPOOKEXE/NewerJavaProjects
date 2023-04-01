package renderer.material;

import renderer.lib.Ray;
import renderer.math.Vector3;

public class MaterialReflectionData {

	public Vector3 scatter_direction;
	public Ray scattered;
	public Vector3 attenuation;
	
	public MaterialReflectionData() {}
}
