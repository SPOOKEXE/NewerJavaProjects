package renderer;

import renderer.geometry.Triangle;
import renderer.intersect.IntersectResult;
import renderer.math.CFrame;
import renderer.math.Vector3;
import renderer.intersect.IntersectManager;

public class Main {

	public static void main(String[] args) {
		
		int count = 5;
		Triangle[] intersectTest = new Triangle[count];
		
		// Generate Triangles
		for (int x = 0; x < count; x++) {
			int x_ = x * 2;
			intersectTest[x] = new Triangle(
				new Vector3(x_, 0, 0),
				new Vector3(x_, 2, 0),
				new Vector3(x_ + 2, 2, 0)
			);
		}
		
		for (Triangle tri : intersectTest) System.out.println(tri.toString());
		
		// Attempt intersects
		CFrame cfDir = new CFrame( new Vector3(0, 0, 3), new Vector3(3, 0, 0) );
		
		IntersectResult intersectResult = null;
		for (Triangle tri : intersectTest) {
			intersectResult = IntersectManager.LineTriangleIntersect( tri, cfDir.Position, cfDir.LookVector );
			if (intersectResult != null) break;
		}
		
		System.out.println(intersectResult);
		
	}

}
