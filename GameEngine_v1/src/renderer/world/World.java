package renderer.world;

import renderer.camera.CameraManager;
import renderer.camera.ICamera;
import renderer.display.DisplayManager;
import renderer.display.IDisplay;
import renderer.entity.EntityManager;
import renderer.entity.IEntity;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class World implements IWorld {

	public EntityManager newEntityManager;
	public DisplayManager newDisplayManager;
	public CameraManager newCameraManager;
	
	private ArrayList<IEntity> entities;
	private ArrayList<IDisplay> displays;
	private ArrayList<ICamera> cameras;
	
	private boolean enabled;
	
	public World() {
		System.out.println("Create a new World");
		
		this.newDisplayManager = new DisplayManager();
		this.newEntityManager = new EntityManager();
		this.newCameraManager = new CameraManager();
		
		this.newDisplayManager.init();
		this.newEntityManager.init();
		this.newCameraManager.init();
		
		this.cameras = this.newCameraManager.getCameras();
		this.displays = ( ArrayList<IDisplay> ) this.newDisplayManager.getDisplays();
		this.entities = this.newEntityManager.entities;
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		this.newDisplayManager.update();
		this.newEntityManager.update();
	}
	
	@Override
	public void clearWorld() {
		System.out.println("Clear World.");
	}
	
	@Override
	public void loadWorld( JSONObject worldJSON ) {
		
		this.clearWorld();
		if (worldJSON == null) {
			System.out.println("No World Data Passed!");
			return;
		}
		
		String worldName = worldJSON.get("name").toString();
		
		System.out.println("Load World (" + worldName + ") : " + worldJSON.toJSONString());
		
		JSONArray cameras = (JSONArray) worldJSON.get("cameras");
		JSONArray entities = (JSONArray) worldJSON.get("objects");
	    JSONArray interfaces = (JSONArray) worldJSON.get("interfaces");
		
		System.out.println("Generated Instances: ");
		for (Object c : cameras)
	    {
			ICamera newCamera = CameraManager.DeserializeJSON( (JSONObject) c );
			System.out.println("-- CAMERA -- " + newCamera.toString() + " > " + c.toString());
			this.cameras.add(newCamera);
	    }
		
		this.newEntityManager.loadEntities(entities, this);
		this.newDisplayManager.loadInterfaces(interfaces, this);
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
			"Camera Count: %s"
		)));
		
		return String.format(
			baseString, 
			Integer.toHexString(this.hashCode()),
			this.entities.size(),
			this.cameras.size()
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

	@Override
	public ArrayList<ICamera> getCameras() {
		return this.cameras;
	}
	
}
