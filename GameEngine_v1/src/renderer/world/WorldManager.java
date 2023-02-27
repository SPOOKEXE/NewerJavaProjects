package renderer.world;

import java.util.ArrayList;
import java.util.List;

import renderer.display.IDisplay;

public class WorldManager {
	
	private List<IWorld> worlds;
	private List<IDisplay> displays;
	
	public WorldManager() {
		this.worlds = new ArrayList<IWorld>();
		this.displays = new ArrayList<IDisplay>();
	}
	
	public List<IWorld> getWorlds() {
		return this.worlds;
	}
	
	public List<IDisplay> getDisplays() {
		return this.displays;
	}
	
	public int countWorlds() {
		return this.worlds.size();
	}
	
	public void addWorlds( ArrayList<IWorld> worlds ) {
		for (IWorld world : worlds) {
			this.worlds.add(world);
			this.displays.addAll( world.getDisplays() );
		}
		System.out.println("Added " + worlds.size() + " Worlds to World Manager.");
	}
	
	public void clearWorlds() {
		System.out.println("Clear Worlds");
	}
	
	public void init() {
		
	}
	
}
