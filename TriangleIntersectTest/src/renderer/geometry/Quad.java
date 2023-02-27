package renderer.geometry;

import renderer.material.BaseMaterial;
import renderer.math.Vector3;

public class Quad {
	
	Vertex v1, v2, v3, v4;
	BaseMaterial material;

	public Quad(Vertex v1, Vertex v2, Vertex v3, Vertex v4, BaseMaterial material) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
		this.material = material;
	}
	
	public Quad(Vector3 v1, Vector3 v2, Vector3 v3, Vector3 v4) {
		this.v1 = new Vertex(v1);
		this.v2 = new Vertex(v2);
		this.v3 = new Vertex(v3);
		this.v4 = new Vertex(v4);
	}

	public Triangle[] trianglify() {
		Triangle[] tris = new Triangle[2];
		tris[0] = new Triangle(this.v1, this.v2, this.v3, this.material);
		tris[1] = new Triangle(this.v1, this.v3, this.v4, this.material);
		return tris;
	}	
	
	@Override
	public String toString() {
		return String.format("Quad | %s, %s, %s, %s", this.v1, this.v2, this.v3, this.v4);
	}
	
}
