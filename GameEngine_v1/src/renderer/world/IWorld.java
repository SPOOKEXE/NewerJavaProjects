package renderer.world;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import renderer.camera.ICamera;
import renderer.display.IDisplay;
import renderer.entity.IEntity;

public interface IWorld {

	ArrayList<IDisplay> getDisplays();
	ArrayList<IEntity> getEntities();
	ArrayList<ICamera> getCameras();
	
	void init();
	void update();
	
	void loadWorld(JSONObject worldJSON);
	void clearWorld();
	
	void setEnabled( boolean enabled );
	boolean getEnabled();
	
	String toString();
	
}
