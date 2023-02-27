package renderer.raycaster;

import java.util.ArrayList;

public class RaycastParams {
	
	public ArrayList<Object> FilterDescendants;
	public renderer.enumerations.RaycastType RaycastType; // 1 = whitelist, 2 = blacklist
	
	public RaycastParams() {
		FilterDescendants = new ArrayList<Object>();
		RaycastType = renderer.enumerations.RaycastType.Blacklist;
	}
	
}
