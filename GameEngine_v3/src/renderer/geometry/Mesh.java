package renderer.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.material.BaseMaterial;

public class Mesh {

	public ArrayList<Triangle> triangles;
	
	public Mesh(List<Triangle> triangles, List<Quad> quads, BaseMaterial material) {
		this.triangles = new ArrayList<Triangle>();
		if (triangles != null) {
			this.appendTris(triangles);
		}
		if (quads != null) {
			this.loadQuads(quads);
		}
		this.setMaterial(material);
	}

	public Mesh(List<Triangle> triangles, List<Quad> quads) {
		this.triangles = new ArrayList<Triangle>();	
		if (triangles != null) {
			this.appendTris( triangles );
		}
		if (quads != null) {
			this.loadQuads( quads );
		}
	}

	public Mesh( Triangle...triangles ) {
		this.triangles = new ArrayList<Triangle>();
		this.appendTris(Arrays.asList(triangles));
	}
	
	public Mesh( Quad...quads ) {
		this.triangles = new ArrayList<Triangle>();
		this.loadQuads( Arrays.asList(quads) );
	}
	
	private void loadQuads(List<Quad> quads) {
		for (Quad quad : quads) {
			this.appendTris(Arrays.asList(quad.trianglify()));
		}
	}
	
	private void appendTris(List<Triangle> tris) {
		this.triangles.addAll(tris);
	}
	
	public void setMaterial(BaseMaterial material) {
		for (Triangle tri : this.triangles) {
			tri.material = material;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Mesh(%s)", this.triangles);
	}
	
}
