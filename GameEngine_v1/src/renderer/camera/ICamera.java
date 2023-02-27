package renderer.camera;

import renderer.point.Point3D;
import renderer.point.Vector2;
import renderer.point.Vector3;

public interface ICamera {
	
	Point3D getPosition();
	Vector3 getDirection();
	Vector2 getResolution();
	
	void setResolution( Vector2 resolution );
	
	double getFOV();
	String getName();
	
	boolean isEnabled();
}
