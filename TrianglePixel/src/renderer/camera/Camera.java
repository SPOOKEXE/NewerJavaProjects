package renderer.camera;

import renderer.math.CFrame;
import renderer.math.Vector2;
import renderer.math.Vector3;

public class Camera implements ICamera {

	private String name = "Camera";

	private CFrame cframe;
	private Vector2 resolution;
	
	private double FOV;
	private boolean enabled;
	
	public Camera() {
		this.setName("Camera");
		this.setCFrame( new CFrame() );
		this.setResolution( new Vector2(1280, 720) );
	}
	
	public Camera(String name) {
		this.setName(name);
		this.setCFrame( new CFrame() );
		this.setResolution( new Vector2(1280, 720) );
	}
	
	@Override
	public String toString() {
		String baseString = "Camera %s | %s | Position: [%s] | Direction: [%s]";
		String hashString = Integer.toHexString(this.hashCode());
		return String.format(baseString, this.name, hashString, this.cframe.getPosition(), this.cframe.getDirection());
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
		return this.cframe.getPosition();
	}

	@Override
	public Vector3 getDirection() {
		return this.cframe.getDirection();
	}

	// UPDATE TO USE RASTERIZATION METHOD
	@Override
	public Vector3 getPixelPosition(Vector2 pixel_position) {
		return this.getPosition().add(new Vector3(pixel_position.x, pixel_position.y, 0));
	}
	
	@Override
	public Vector2 getResolution() {
		return this.resolution;
	}

	@Override
	public boolean getEnabled() {
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
		this.cframe.setPosition(position);
	}

	@Override
	public void setDirection(Vector3 lookAt) {
		this.cframe.setDirection(lookAt);
	}
	
	@Override
	public void setCFrame(CFrame newCFrame) {
		this.cframe = newCFrame;
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
