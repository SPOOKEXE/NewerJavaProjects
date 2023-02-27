package renderer.entity;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import renderer.entity.builder.BasicEntityBuilder;
import renderer.point.Point3D;
import renderer.point.Vector3;
import renderer.world.World;

public class EntityManager {

	public ArrayList<IEntity> entities;
	
	public EntityManager() {
		System.out.println("Entity Manager");
		this.entities = new ArrayList<IEntity>();
	}

	public void loadEntities(JSONArray entities, World world) {
		for (Object c : entities)
	    {
			IEntity newEntity = EntityManager.DeserializeJSON( (JSONObject) c, world );
			System.out.println("-- ENTITY -- " + newEntity.toString() + " > " + c.toString());
			this.entities.add(newEntity);
	    }
	}
	
	public static IEntity DeserializeJSON(JSONObject c, World world) {

		IEntity shape = null;
		
		JSONArray raw_pos = (JSONArray) c.get("position");
		double x = Double.valueOf( raw_pos.get(0).toString() );
		double y = Double.valueOf( raw_pos.get(1).toString() );
		double z = Double.valueOf( raw_pos.get(2).toString() );
		Point3D position = new Point3D( x, y, z );
		
		JSONArray raw_rot = (JSONArray) c.get("rotation");
		double xRot = Double.valueOf( raw_rot.get(0).toString() );
		double yRot = Double.valueOf( raw_rot.get(1).toString() );
		double zRot = Double.valueOf( raw_rot.get(2).toString() );
		Vector3 direction = new Vector3(xRot, yRot, zRot);
		
		switch ( (String) c.get("shape") ) {
			case "cube":
				double size = Double.valueOf( c.get("size").toString() );
				shape = BasicEntityBuilder.createCube(size , position, direction);
				break;
			default:
				break;
		}
		
		return shape;
		
	}
	
	public void init() {
		
	}
	
	public void update() {
		
	}

}
