package renderer.camera;

import renderer.math.Vector2;
import renderer.math.Vector3;

public interface ICamera {
	String getName();
	double getFOV();
	Vector3 getPosition();
	Vector3 getDirection();
	Vector2 getResolution();
	boolean isEnabled();
	
	void setName(String name);
	void setFOV(double FOV);
	void setPosition( Vector3 position );
	void setDirection( Vector3 direction );
	void setResolution( Vector2 resolution );
	void setEnabled( boolean enabled );
	
	String toString();
}
