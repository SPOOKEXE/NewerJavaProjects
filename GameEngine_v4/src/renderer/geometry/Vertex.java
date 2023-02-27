package renderer.geometry;

import renderer.math.Vector3;

public class Vertex {

	public float x;
	public float y;
	public float z;
	
	public Vertex(float x, float y , float z) {
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
	
	@Override
	public String toString() {
		return String.format("Vertex(%s, %s, %s)", this.x, this.y, this.z);
	}
	
}
