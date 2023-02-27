package renderer.world;

import java.util.ArrayList;

import renderer.entity.IEntity;
import renderer.visualizer.IDisplay;

public interface IWorld {

	ArrayList<IDisplay> getDisplays();
	ArrayList<IEntity> getEntities();
	// ArrayList<ICamera> getCameras();
	
	void init();
	void update();
	
	// void loadWorld(JSONObject worldJSON);
	void clearWorld();
	
	void setEnabled( boolean enabled );
	boolean getEnabled();
	
	String toString();
	
	void addEntities(IEntity... entities);
	// void addCameras(ICamera... cameras);
	void addDisplays(IDisplay... displays);
}
