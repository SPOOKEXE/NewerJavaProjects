package renderer.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.math.Vector3;

public class BoundingBox {
	public Vector3 size;
	public Vector3 p0;
	public Vector3 p1;
	public ArrayList<Vector3> points;
	
	public BoundingBox() {
		this.points = new ArrayList<Vector3>();
		this.p0 = new Vector3();
		this.p1 = new Vector3();
		this.size = new Vector3();
		this.updatep0p1();
	}
	
	public BoundingBox(Vector3 p0, Vector3 p1) {
		this.points = new ArrayList<Vector3>();
		this.p0 = p0;
		this.p1 = p1;
		this.size = p1.sub(p0);
		this.updatep0p1();
	}
	
	public boolean contains(Vector3 p) {
		return (p.x >= this.p0.x && p.x <= this.p1.x && p.y >= this.p0.y && p.y <= this.p1.y);
	}
	
	public void expand(Vector3 p) {
		// expand size to cover this point
		this.points.add(p);
		this.updatep0p1();
	}
	
	public void expandAll(Vector3...points) {
		this.points.addAll( Arrays.asList(points) );
		this.updatep0p1();
	}
	
	public void expandAll(ArrayList<Vector3> points) {
		this.points.addAll( points );
		this.updatep0p1();
	}
	
	public void remove(Vector3 p) {
		this.points.remove(p);
	}
	
	public void removeAll(Vector3...points) {
		this.points.removeAll( Arrays.asList(points) );
	}
	
	private void updatep0p1() {
		// starting hull
		float sx = this.p0.x;
		float sy = this.p0.y;
		float sz = this.p0.z;
		
		float fx = this.p1.x;
		float fy = this.p1.y;
		float fz = this.p1.z;
		
		for (Vector3 point : this.points) {
			if (point.x > sx) sx = point.x;
			if (point.y > sy) sy = point.y;
			if (point.z > sz) sz = point.z;
			if (point.x < fx) fx = point.x;
			if (point.y < fy) fy = point.y;
			if (point.z < fz) fz = point.z;
		}
		
		this.p0 = new Vector3(sx, sy, sz);
		this.p1 = new Vector3(fx, fy, fz);
		this.size = this.p1.sub(this.p0);
	}
	
	public void fromMesh(Mesh mesh) {
		ArrayList<Vector3> newPoints = new ArrayList<Vector3>(mesh.triangles.size() * 3);
		for (Triangle tri : mesh.triangles) {
			newPoints.addAll( Arrays.asList(tri.getPoints()) );
		}
		this.points = new ArrayList<Vector3>();
		this.p0 = points.get(0);
		this.p1 = points.get(0);
		this.expandAll(newPoints);
	}
	
	// Returns a bounding box that encapsulates the entire mesh (all triangles, all vertices)
	public static BoundingBox GetBoundingBoxForMeshes(List<Mesh> meshes) {
		Vector3 origin = meshes.get(0).triangles.get(0).getPoints()[0];
		BoundingBox newBounds = new BoundingBox(origin, origin);
		for (Mesh mesh : meshes) {
			for (Triangle tri : mesh.triangles) {
				newBounds.expandAll(
					tri.v1.toVector3(),
					tri.v2.toVector3(),
					tri.v3.toVector3()
				);
			}
		}
		return newBounds;
	}
	
	public static BoundingBox GetBoundingBoxForMeshes(Mesh...meshes) {
		return BoundingBox.GetBoundingBoxForMeshes(meshes);
	}
	
	@Override
	public String toString() {
		return String.format("BoundingBox(%s, %s, %s)", this.points.size(), this.p0, this.p1);
	}
}
