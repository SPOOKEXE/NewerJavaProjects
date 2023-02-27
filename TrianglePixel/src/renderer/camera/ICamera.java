package renderer.camera;

import renderer.math.CFrame;
import renderer.math.Vector2;
import renderer.math.Vector3;

public interface ICamera {
	String getName();
	double getFOV();
	Vector3 getPosition();
	Vector3 getDirection();
	Vector2 getResolution();
	boolean getEnabled();
	
	void setName(String name);
	void setFOV(double FOV);
	void setPosition( Vector3 position );
	void setDirection( Vector3 direction );
	void setResolution( Vector2 resolution );
	void setEnabled( boolean enabled );
	
	void setCFrame(CFrame cframe);
	
	String toString();
	Vector3 getPixelPosition(Vector2 pixel_position);
}
