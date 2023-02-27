package renderer.camera;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import renderer.point.Point3D;
import renderer.point.Vector3;
import renderer.shapes.MyPolygon;
import renderer.shapes.MyPolyhedron;

public class CameraManager {

	private ArrayList<ICamera> cameras;
	
	public CameraManager() {
		System.out.println("Camera Manager");
		this.cameras = new ArrayList<ICamera>();
	}
	
	public ArrayList<ICamera> getCameras() {
		return this.cameras;
	}
	
	public ICamera getCameraFromName( String cameraName ) {
		for (ICamera camera : this.cameras) {
			if (camera.getName().equals(cameraName)) {
				return camera;
			}
		}
		return null;
	}
	
	public void init() {
		
	}
	
	public static ICamera DeserializeJSON( JSONObject c ) {
		String cameraName = String.valueOf( c.get("name") );
		JSONArray raw_pos = (JSONArray) c.get("position");
		double x = Double.valueOf( raw_pos.get(0).toString() );
		double y = Double.valueOf( raw_pos.get(1).toString() );
		double z = Double.valueOf( raw_pos.get(2).toString() );
		Point3D position = new Point3D( x, y, z );
		JSONArray raw_dir = (JSONArray) c.get("direction");
		double xDir = Double.valueOf( raw_dir.get(0).toString() );
		double yDir = Double.valueOf( raw_dir.get(1).toString() );
		double zDir = Double.valueOf( raw_dir.get(2).toString() );
		Vector3 direction = new Vector3(xDir, yDir, zDir);
		return new Camera( cameraName, position, direction);
	}
	
	public static boolean PolyhedronInView( ICamera camera, MyPolyhedron polyhedron ) {
		return true;
	}
	
	public static boolean PolygonInView( ICamera camera, MyPolygon polygon ) {
		return true;
	}
	
}
