package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import renderer.camera.Camera;
import renderer.camera.ICamera;
import renderer.debug.DebugDump;
import renderer.math.Vector2;
import renderer.math.Vector3;
import renderer.primitives.Mesh;
import renderer.rendering.RenderManager;
import renderer.shapes.BasicEntityBuilder;

public class Main {

	public static void main(String[] args) {
		ArrayList<Mesh> meshes = new ArrayList<Mesh>();
		meshes.add( BasicEntityBuilder.createSphere(10, 15, new Vector3(20, 10, 0), new Vector3(), Color.RED) );
		// meshes.add( BasicEntityBuilder.createCube(10, new Vector3(), new Vector3(), Color.RED) );
		
		ICamera camera = new Camera("Camera1");
		camera.setPosition(new Vector3(0, 5, -5));
		camera.setDirection(new Vector3(-1, 0, 0));
		camera.setResolution(new Vector2(1280, 720));
		BufferedImage bufferedImage = RenderManager.generateImage( meshes, camera );
		
		DebugDump.DumpCamera( camera );
		//DebugDump.DumpMeshes( meshes );
		DebugDump.DumpAllToFile();

		DebugDump.DumpImageToFile(bufferedImage);
	}

}
