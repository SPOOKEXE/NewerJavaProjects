package renderer.raycaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.entity.IEntity;
import renderer.point.Point3D;
import renderer.point.Vector3;
import renderer.shapes.MyPolygon;

public class Raycaster {

	// static methods //
	public static RaycastResult Raycast(ArrayList<IEntity> entities, Point3D origin, Vector3 direction, RaycastParams params) {
		
		List<MyPolygon> polygons = new ArrayList<MyPolygon>();
		for (IEntity entity : entities) {
			polygons.addAll( Arrays.asList(entity.getPolygons()) );
		}
		
		System.out.println( polygons.size() );
		
		return null;
	}
	
	public static RaycastResult Raycast(ArrayList<IEntity> entities, Point3D origin, Vector3 direction) {
		RaycastParams params = new RaycastParams();
 
		return Raycast(entities, origin, direction, params);
	}
	
}
