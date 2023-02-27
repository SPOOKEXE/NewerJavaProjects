package renderer.camera;

import renderer.math.Vector2;
import renderer.math.Vector3;

public class Camera implements ICamera {
	private String name = "Camera";
	
	private Vector3 position;
	private Vector3 direction;
	private Vector2 resolution;
	
	private double FOV;
	private boolean enabled;
	
	public Camera( Vector3 position, Vector3 direction ) {
		this.setName("Camera");
		this.setPosition(position);
		this.setDirection(direction);
		this.setResolution( new Vector2(1280, 720) );
	}
	
	public Camera( Vector3 position, Vector3 direction, Vector2 resolution ) {
		this.setName("Camera");
		this.setPosition(position);
		this.setDirection(direction);
		this.setResolution(resolution);
	}
	
	public Camera( String name, Vector3 position, Vector3 direction ) {
		this.setName(name);
		this.setPosition(position);
		this.setDirection(direction);
		this.setResolution( new Vector2(1280, 720) );
	}
	
	public Camera( String name, Vector3 position, Vector3 direction, Vector2 resolution ) {
		this.setName(name);
		this.setPosition(position);
		this.setDirection(direction);
		this.setResolution(resolution);
	}
	
	@Override
	public String toString() {
		String baseString = "Camera %s | %s | Position: [%s] | Direction: [%s]";
		String hashString = Integer.toHexString(this.hashCode());
		return String.format(baseString, this.name, hashString, this.position, this.direction);
	}

	// CLASS METHODS //
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getFOV() {
		return this.FOV;
	}
	
	@Override
	public Vector3 getPosition() {
		return this.position;
	}

	@Override
	public Vector3 getDirection() {
		return this.direction;
	}

	@Override
	public Vector2 getResolution() {
		return this.resolution;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setFOV(double FOV) {
		this.FOV = FOV;
	}
	
	@Override
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	@Override
	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}

	@Override
	public void setResolution(Vector2 resolution) {
		this.resolution = resolution;
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
