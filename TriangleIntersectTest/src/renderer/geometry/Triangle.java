package renderer.geometry;

import renderer.material.BaseMaterial;
import renderer.math.Vector3;

public class Triangle {

	public Vertex v1, v2, v3;
	public BaseMaterial material;
	
	public Triangle(Vector3 v1, Vector3 v2, Vector3 v3) {
		this.v1 = new Vertex(v1);
		this.v2 = new Vertex(v2);
		this.v3 = new Vertex(v3);
		this.setMaterial(new BaseMaterial());
	}
	
	public Triangle(Vertex v1, Vertex v2, Vertex v3, BaseMaterial material) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.setMaterial(material);
	}
	
	public Triangle(Vector3 v1, Vector3 v2, Vector3 v3, BaseMaterial material) {
		this.v1 = new Vertex(v1);
		this.v2 = new Vertex(v2);
		this.v3 = new Vertex(v3);
		this.setMaterial(material);
	}
	
	public void setMaterial(BaseMaterial material) {
		this.material = material;
	}
	
	public Triangle offsetTriangle(Vector3 offset) {
		return new Triangle(
			this.v1.toVector3().add(offset),
			this.v2.toVector3().add(offset),
			this.v3.toVector3().add(offset),
			this.material
		);
		
	}
	
	public Vector3[] getPoints() {
		return new Vector3[] { this.v1.toVector3(), this.v2.toVector3(), this.v3.toVector3() };
	}

	@Override
	public String toString() {
		return String.format("Triangle | %s, %s, %s | %s", this.v1, this.v2, this.v3, this.material);
	}
}
