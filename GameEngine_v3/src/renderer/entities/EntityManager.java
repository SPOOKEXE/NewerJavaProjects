package renderer.entities;

import java.util.ArrayList;

public class EntityManager {

	public ArrayList<IEntity> entities;
	
	public EntityManager() {
		System.out.println("Entity Manager");
		this.entities = new ArrayList<IEntity>();
	}
	
}
