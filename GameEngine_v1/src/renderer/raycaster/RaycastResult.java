package renderer.raycaster;

import java.awt.Color;

import renderer.entity.IEntity;
import renderer.point.Point3D;
import renderer.point.Vector3;

public class RaycastResult {

	public IEntity entity = null;
	public Point3D position = new Point3D();
	public Vector3 normal = new Vector3();
	public Color color = Color.WHITE;
	
	public RaycastResult() {
		
	}
	
}
