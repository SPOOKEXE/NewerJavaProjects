package renderer.raycast;

import java.util.ArrayList;

import renderer.primitives.Mesh;
import renderer.primitives.Triangle;

public class RaycastManager {

	// Crude implementation
	public static RaycastResult ResolveRaycast( ArrayList<Mesh> meshes, Raycast _raycast) {
		
		RaycastResult rayResult = null;
		
		for (Mesh mesh : meshes) {
			for (Triangle triangle : mesh.triangles) {
				IntersectResult result = IntersectManager.LineTriangleIntersect( triangle, _raycast.origin, _raycast.direction );
				if (result != null) {
					rayResult = new RaycastResult(mesh, result.position, result.normal, triangle);
					break;
				}
			}
		}
		
		// DebugDump.DumpRaycastAttempt(_raycast.origin, _raycast.direction, _raycast.parameters, rayResult);
		
		return rayResult;
	}

}