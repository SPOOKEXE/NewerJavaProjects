package renderer.display;

import java.util.ArrayList;
import java.util.List;
import java.awt.Canvas;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import renderer.camera.ICamera;
import renderer.display.variations.Viewport;
import renderer.world.World;

public class DisplayManager extends Canvas {

	private static final long serialVersionUID = 1L;
	
	private List<IDisplay> displays;
	
	public DisplayManager() {
		System.out.println("Start Display Manager");
		this.displays = new ArrayList<IDisplay>();
	}
	
	public void init() {
		for (IDisplay display : this.displays) {
			display.init();
		}
	}
	
	public void update() {
		for (IDisplay display : this.displays) {
			display.update();
		}
	}
	
	public void addDisplay( IDisplay display ) {
		this.displays.add(display);
	}
	
	public List<IDisplay> getDisplays() {
		return this.displays;
	}
	
	public void loadInterfaces( JSONArray interfaceArray, World world ) {
		for (Object interfaceData : (JSONArray) interfaceArray) {
			IDisplay newDisplayObject = DeserializeJSON( (JSONObject) interfaceData, world);
			System.out.println("-- INTERFACE -- " + newDisplayObject.toString() + " > " + interfaceData.toString());
			this.addDisplay(newDisplayObject);
		}
	}
	
	public static IDisplay DeserializeJSON(JSONObject c, World world) {
		
		IDisplay display = null;
		
		String displayType = c.get("type").toString();
		JSONArray raw_size = (JSONArray) c.get("size");
		int xSize = Integer.valueOf( raw_size.get(0).toString() );
		int ySize = Integer.valueOf( raw_size.get(1).toString() );
		
		switch( displayType ) {
			case "Viewport":
				String cameraName = c.get("camera").toString();
				ICamera targetCamera = world.newCameraManager.getCameraFromName(cameraName);
				System.out.println(targetCamera);
				Viewport viewport = new Viewport();
				viewport.setSize(xSize, ySize);
				viewport.setCameraAndWorld(targetCamera, world );
				display = viewport;
			default:
				break;
		}
		
		if (display != null) {
			display.init();
		}
		
		return display;
		
	}
	
}
