package renderer.camera;

import java.util.ArrayList;

public class CameraManager {

private ArrayList<ICamera> cameras;
	
	public CameraManager() {
		System.out.println("Camera Manager");
		this.cameras = new ArrayList<ICamera>();
	}
	
	public ArrayList<ICamera> getCameras() {
		return this.cameras;
	}
	
	public ICamera getCameraFromName( String cameraName ) {
		for (ICamera camera : this.cameras) {
			if (camera.getName().equals(cameraName)) {
				return camera;
			}
		}
		return null;
	}
	
	public void init() {
		
	}
	
}
