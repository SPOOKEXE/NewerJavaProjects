package renderer.camera;

import renderer.point.Point3D;
import renderer.point.Vector2;
import renderer.point.Vector3;

public class Camera implements ICamera {

	private Point3D position;
	private Vector3 direction;
	private Vector2 resolution;
	
	private String name;
	private double FOV;
	
	private boolean enabled;
	
	public Camera( String name, Point3D position, Vector3 direction ) {
		this.name = name;
		this.position = position;
		this.direction = direction;
		this.resolution = new Vector2(1280, 720);
		this.FOV = 70;
		this.enabled = true;
	}
	
	public Camera( String name, Point3D position, Vector3 direction, double FOV) {
		this.name = name;
		this.position = position;
		this.direction = direction;
		this.resolution = new Vector2(1280, 720);
		this.FOV = FOV;
		this.enabled = true;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Point3D getPosition() {
		return this.position;
	}
	
	public Vector3 getDirection() {
		return this.direction;
	}
	
	public double getFOV() {
		return this.FOV;
	}
	
	public Vector2 getResolution() {
		return this.resolution;
	}
	
	public void setResolution( Vector2 resolution ) {
		this.resolution = resolution;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	@Override
	public String toString() {
		String baseString = "Camera | %s | Position: [%s] | Direction: [%s]";
		return String.format(
			baseString,
			Integer.toHexString(this.hashCode()),
			this.position.toString(), 
			this.direction.toString()
		);
	}
	
}
