package renderer.raycast;

import java.util.ArrayList;

import renderer.primitives.Mesh;

public class RaycastParams {

	public renderer.enumerations.RaycastType RaycastType;
	public ArrayList<Mesh> Meshes;
	
	public RaycastParams() {
		this.Meshes = new ArrayList<Mesh>();
		this.RaycastType = renderer.enumerations.RaycastType.Blacklist;
	}
	
	@Override
	public String toString() {
		return String.format("RaycastParams ( %s, %s )", this.Meshes.size(), this.RaycastType.toString());
	}
	
}