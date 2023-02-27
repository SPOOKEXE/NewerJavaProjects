package renderer.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import renderer.camera.ICamera;
import renderer.math.Vector2;
import renderer.math.Vector3;
import renderer.primitives.Mesh;
import renderer.raycast.Raycast;
import renderer.raycast.RaycastManager;
import renderer.raycast.RaycastResult;

public class RenderManager {

	public static Color getPixelColorFromRay( ArrayList<Mesh> meshes, Vector3 origin, Vector3 direction ) {
		Color color = Color.BLACK;
		Raycast _raycast = new Raycast(origin, direction);
		RaycastResult result = RaycastManager.ResolveRaycast(meshes, _raycast);
		if (result != null && result.Triangle.color != null) {
			//System.out.println(_raycast);
			//System.out.println(result);
			color = result.Triangle.color;
		}
		return color;
	}
	
	public static BufferedImage generateImage( ArrayList<Mesh> meshes, ICamera camera ) {
		
		// Prepare an image to be written to
		Vector2 imageResolution = camera.getResolution();
		BufferedImage newImage = new BufferedImage(
			(int) imageResolution.x,
			(int) imageResolution.y,
			BufferedImage.TYPE_INT_ARGB
		);
		
		// Get the camera position / direction
		Vector3 cameraPosition = camera.getPosition();
		Vector3 cameraDirection = camera.getDirection();
		
		for (int x = 0; x < imageResolution.x; x++ ) {
			for (int y = 0; y < imageResolution.y; y++ ) {
				// NOTE: DOES NOT ROTATE CORRECTLY : RESOLVE MATH ISSUE
				Vector3 position = cameraPosition.copy().add(new Vector3(x, y, 0).mult(0.025));
				Color color = getPixelColorFromRay(meshes, position, cameraDirection);
				//System.out.println(x + " || " + y + " || " + color.toString() + " || " + position);
				System.out.println(x + " || " + y + " || " + color.toString());
				newImage.setRGB(x, y, color.getRGB());
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}

		return newImage;
	}
}
