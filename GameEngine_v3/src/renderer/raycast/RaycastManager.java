package renderer.raycast;

import renderer.entities.IEntity;
import renderer.geometry.BoundingBox;
import renderer.geometry.Triangle;
import renderer.intersect.IntersectManager;
import renderer.intersect.IntersectResult;
import renderer.math.Vector3;
import renderer.world.IWorld;

public class RaycastManager {

	// Crude implementation
	public static RaycastResult ResolveRaycast( IWorld world, Raycast _raycast) {
		for (IEntity entity : world.getEntities()) {
			BoundingBox entityBounds = entity.getBounds();
			Vector3 entityPosition = entity.getPosition();
			
			//Vector3 p0 = entityPosition.add(entityBounds.p0);
			//Vector3 p1 = entityPosition.add(entityBounds.p1);
			
			Vector3[] boundIntersect = IntersectManager.LineIntersectAxisAlignedBox(_raycast.origin, _raycast.direction, entityBounds.p0, entityBounds.p1);
			if (boundIntersect == null) {
				continue;
			}
			
			Vector3 closePoint = boundIntersect[0];
			// Vector3 farPoint = boundIntersect[1];
			// return new RaycastResult(entity, closePoint, _raycast.direction.mult(-1), entity.getTriangles().get(0));
			
			//System.out.println("Intersected BoundingBox @ " + closePoint);
			//System.out.println("Triangles count for mesh; " + entity.getTriangles().size());
			
			for (Triangle triangle : entity.getTriangles()) {
				triangle = triangle.offsetTriangle(closePoint);
				IntersectResult result = IntersectManager.LineTriangleIntersect( triangle, closePoint, _raycast.direction );
				if (result != null) {
					System.out.println(result);
					return new RaycastResult(entity, result.position, result.normal, triangle);
				}
			}
		}
		return null;
	}
	
	// Implement rasterization

}