package renderer.world;

import java.util.ArrayList;

import renderer.entities.IEntity;
public interface IWorld {

	// void loadWorld(JSONObject worldJSON);
	void clearWorld();
	void setEnabled( boolean enabled );
	void addEntities(IEntity... entities);
	void addEntities(ArrayList<IEntity> entities);

	ArrayList<IEntity> getEntities();
	boolean getEnabled();
	String toString();
	
	
}
