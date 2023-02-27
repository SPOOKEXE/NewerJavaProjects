package renderer.world;

import java.util.ArrayList;
import java.util.Arrays;

import renderer.entity.EntityManager;
import renderer.entity.IEntity;
import renderer.visualizer.DisplayManager;
import renderer.visualizer.IDisplay;

public class World implements IWorld {

	public EntityManager newEntityManager;
	public DisplayManager newDisplayManager;
	// public CameraManager newCameraManager;
	
	private ArrayList<IEntity> entities;
	private ArrayList<IDisplay> displays;
	// private ArrayList<ICamera> cameras;
	
	private boolean enabled;
	
	public World() {
		System.out.println("Create a New World");
		
		this.newDisplayManager = new DisplayManager();
		this.newEntityManager = new EntityManager();
		// this.newCameraManager = new CameraManager();
		
		this.newDisplayManager.init();
		this.newEntityManager.init();
		// this.newCameraManager.init();
		
		// this.cameras = this.newCameraManager.getCameras();
		this.displays = ( ArrayList<IDisplay> ) this.newDisplayManager.getDisplays();
		this.entities = this.newEntityManager.entities;
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		// this.newDisplayManager.update();
		this.newEntityManager.update();
	}
	
	@Override
	public void clearWorld() {
		System.out.println("Clear World.");
	}
	
	@Override
	public void setEnabled( boolean enabled ) {
		this.enabled = enabled;
	}

	@Override
	public boolean getEnabled() {
		return this.enabled;
	}
	
	@Override
	public String toString() {
		
		String baseString = String.join(" | ", new ArrayList<String>(Arrays.asList(
			"World | %s", 
			"Entity Count: %s",
			"Display Count: %s"
		)));
		
		return String.format(
			baseString, 
			Integer.toHexString(this.hashCode()),
			this.entities.size(),
			this.displays.size()
		);
		
	}

	@Override
	public ArrayList<IDisplay> getDisplays() {
		return this.displays;
	}

	@Override
	public ArrayList<IEntity> getEntities() {
		return this.entities;
	}

//	@Override
//	public ArrayList<ICamera> getCameras() {
//		return this.cameras;
//	}
	
	@Override
	public void addEntities(IEntity...entities) {
		this.entities.addAll( Arrays.asList(entities) );
	}
	
//	@Override
//	public void addCameras(ICamera...cameras) {
//		this.cameras.addAll( Arrays.asList(cameras) );
//	}
	
	@Override
	public void addDisplays(IDisplay...displays) {
		this.displays.addAll( Arrays.asList(displays) );
	}
	
}
