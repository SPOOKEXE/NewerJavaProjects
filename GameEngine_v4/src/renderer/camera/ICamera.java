package renderer.camera;

import renderer.math.CFrame;
import renderer.math.Vector2;
import renderer.math.Vector3;

public interface ICamera {
	// GETS
	String getName();
	float getFOV();
	float getFocalLength();
	CFrame getCFrame();
	Vector3 getPosition();
	Vector3 getLookVector();
	Vector3 getRightVector();
	Vector3 getUpVector();
	Vector2 getResolution();
	boolean isEnabled();
	// SETS
	void toDefault();
	void setName(String name);
	void setFOV(float FOV);
	void setPosition( Vector3 position );
	void setCFrame(CFrame cframe);
	void setResolution( Vector2 resolution );
	void setEnabled( boolean enabled );
	// MISC
	String toString();
}
