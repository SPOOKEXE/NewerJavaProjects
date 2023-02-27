package renderer.camera;

import renderer.math.CFrame;
import renderer.math.Vector2;
import renderer.math.Vector3;

public class Camera implements ICamera {
	// Variables - Fields
	private String name = "Camera";
	
	private CFrame cframe;
	private Vector2 resolution;
	
	private float FOV;
	private float FocalLength;
	private boolean enabled;
	
	private static Vector2 defaultResolution = new Vector2(640, 360);
	
	// Constructors //
	public Camera( Vector3 position, Vector3 lookAt ) {
		this.toDefault();
		this.setCFrame( new CFrame(position, lookAt) );
	}
	
	public Camera( Vector3 position, Vector3 lookAt, Vector2 resolution ) {
		this.toDefault();
		this.setCFrame( new CFrame(position, lookAt) );
		this.setResolution(resolution);
	}

	public Camera( String name, Vector3 position, Vector3 lookAt ) {
		this.toDefault();
		this.setName(name);
		this.setCFrame( new CFrame(position, lookAt) );
	}
	
	public Camera( String name, Vector3 position, Vector3 lookAt, Vector2 resolution ) {
		this.toDefault();
		this.setName(name);
		this.setCFrame( new CFrame(position, lookAt) );
		this.setResolution(resolution);
	}
	
	// Methods //
	@Override
	public void toDefault() {
		this.setName("Camera");
		this.setResolution(defaultResolution);
		this.FocalLength = 50;
		this.FOV = 70;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getFOV() {
		return this.FOV;
	}

	@Override
	public CFrame getCFrame() {
		return this.cframe;
	}

	@Override
	public Vector3 getLookVector() {
		return this.cframe.LookVector;
	}

	@Override
	public Vector3 getRightVector() {
		return this.cframe.RightVector;
	}

	@Override
	public Vector3 getUpVector() {
		return this.cframe.UpVector;
	}
	
	@Override
	public Vector3 getPosition() {
		return this.cframe.Position;
	}

	@Override
	public Vector2 getResolution() {
		return this.resolution;
	}

	@Override
	public float getFocalLength() {
		return this.FocalLength;
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
	public void setFOV(float FOV) {
		this.FOV = FOV;
	}

	@Override
	public void setPosition(Vector3 position) {
		this.cframe = new CFrame(position, position.add(this.cframe.LookVector));
	}

	@Override
	public void setCFrame(CFrame cframe) {
		System.out.println(cframe.Position);
		this.cframe = cframe;
	}

	@Override
	public void setResolution(Vector2 resolution) {
		this.resolution = resolution;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		String baseString = "Camera %s | %s | Position: [%s] | Direction: [%s]";
		String hashString = Integer.toHexString(this.hashCode());
		return String.format(baseString, this.name, hashString, this.cframe.Position, this.cframe.LookVector);
	}

}
