package renderer.raycast;

import renderer.entity.IEntity;
import renderer.primitives.Triangle;
import renderer.world.IWorld;

public class RaycastManager {

	// Crude implementation
	public static RaycastResult ResolveRaycast( IWorld world, Raycast _raycast) {
		
		for (IEntity entity : world.getEntities()) {
			for (Triangle triangle : entity.getTriangles()) {
				IntersectResult result = IntersectManager.LineTriangleIntersect( triangle, _raycast.origin, _raycast.direction );
				if (result != null) {
					System.out.println(result);
					return new RaycastResult(entity, result.position, result.normal, triangle);
				}
			}
		}
		
		return null;
	}

}