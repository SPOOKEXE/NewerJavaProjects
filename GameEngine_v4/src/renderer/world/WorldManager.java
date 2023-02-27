package renderer.world;

import java.util.ArrayList;
import java.util.List;

public class WorldManager {

	private List<IWorld> worlds;

	public WorldManager() {
		this.worlds = new ArrayList<IWorld>();
	}
	
	public List<IWorld> getWorlds() {
		return this.worlds;
	}
	
	public int countWorlds() {
		return this.worlds.size();
	}
	
	public void addWorlds( ArrayList<IWorld> worlds ) {
		for (IWorld world : worlds) {
			this.worlds.add(world);
		}
		System.out.println("Added " + worlds.size() + " Worlds to World Manager.");
	}
	
	public void clearWorlds() {
		System.out.println("Clear Worlds");
	}
	
	public void init() {
		
	}
	
}
