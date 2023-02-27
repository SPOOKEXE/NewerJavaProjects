package renderer.primitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mesh {

	public ArrayList<Triangle> triangles;

	public Mesh(List<Triangle> triangles, List<Quad> quads) {
		this.triangles = new ArrayList<Triangle>();
		if (triangles != null) {
			this.triangles.addAll(triangles);
		}
		if (quads != null) {
			for (Quad quad : quads) {
				triangles.addAll( Arrays.asList(quad.trianglify()) );
			}
		}
	}

	public Mesh( Triangle...triangles ) {
		this.triangles = new ArrayList<Triangle>();
		this.triangles.addAll( Arrays.asList(triangles) );
	}
	
	public Mesh( Quad...quads ) {
		this.triangles = new ArrayList<Triangle>();
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		for (Quad quad : Arrays.asList(quads)) {
			List<Triangle> tris = Arrays.asList(quad.trianglify());
			this.triangles.addAll(tris);
		}
		this.triangles.addAll(triangles);
	}

}