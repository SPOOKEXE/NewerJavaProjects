package renderer.world;

import java.util.ArrayList;
import java.util.Arrays;

import renderer.debug.RenderingDebugOutput;
import renderer.entities.EntityManager;
import renderer.entities.IEntity;
import renderer.visualizer.DisplayManager;
import renderer.visualizer.IDisplay;

public class World implements IWorld {

	public EntityManager newEntityManager;
	public DisplayManager newDisplayManager;
	
	private ArrayList<IEntity> entities;
	private ArrayList<IDisplay> displays;
	
	private boolean enabled;
	
	public World() {
		System.out.println("Create a New World");
		
		this.newDisplayManager = new DisplayManager();
		this.newEntityManager = new EntityManager();
		
		this.displays = ( ArrayList<IDisplay> ) this.newDisplayManager.getDisplays();
		this.entities = this.newEntityManager.entities;
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

	@Override
	public void addEntities(IEntity...entities) {
		for (IEntity ient : entities) {
			RenderingDebugOutput.DumpIEntity(ient);
		}
		this.entities.addAll( Arrays.asList(entities) );
	}
	
	@Override
	public void addEntities(ArrayList<IEntity> entities) {
		for (IEntity ient : entities) {
			RenderingDebugOutput.DumpIEntity(ient);
		}
		this.entities.addAll( entities );
	}
	
	@Override
	public void addDisplays(IDisplay...displays) {
		this.displays.addAll( Arrays.asList(displays) );
	}

}
