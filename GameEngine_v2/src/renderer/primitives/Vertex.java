package renderer.primitives;

import renderer.math.Vector3;

public class Vertex {

	public double x;
	public double y;
	public double z;
	
	public Vertex(double x, double y , double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vertex( Vector3 vec3 ) {
		this.x = vec3.x;
		this.y = vec3.y;
		this.z = vec3.z;
	}
	
	public Vector3 toVector3() {
		return new Vector3(this.x, this.y, this.z);
	}
	
	/*public void resize(double scale) {
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
	}*/
	
	@Override
	public String toString() {
		return String.format("Vertex(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
