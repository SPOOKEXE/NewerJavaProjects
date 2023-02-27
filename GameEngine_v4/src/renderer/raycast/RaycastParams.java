package renderer.raycast;

import java.util.ArrayList;

import renderer.entities.IEntity;

public class RaycastParams {

	public renderer.enumerations.RaycastType RaycastType;
	public ArrayList<IEntity> Entities;
	
	public RaycastParams() {
		this.Entities = new ArrayList<IEntity>();
		this.RaycastType = renderer.enumerations.RaycastType.Blacklist;
	}
	
	@Override
	public String toString() {
		return String.format("RaycastParams ( %s, %s )", this.Entities.size(), this.RaycastType.toString());
	}
	
}