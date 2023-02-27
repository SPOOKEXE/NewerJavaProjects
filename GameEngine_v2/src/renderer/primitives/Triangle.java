package renderer.primitives;

import java.awt.Color;

import renderer.math.Vector3;

public class Triangle {

	public Vertex v1, v2, v3;
	public Color color;
	
	public Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.color = color;
	}

	public Triangle(Vector3 v1, Vector3 v2, Vector3 v3) {
		this.v1 = new Vertex(v1);
		this.v2 = new Vertex(v2);
		this.v3 = new Vertex(v3);
		this.color = Color.RED;
	}
	
	@Override
	public String toString() {
		return String.format("Triangle | %s, %s, %s", this.v1, this.v2, this.v3);
	}
	
	/*public void resize(double scale) {
		this.v1.resize(scale);
		this.v2.resize(scale);
		this.v3.resize(scale);
	}*/
	
}
