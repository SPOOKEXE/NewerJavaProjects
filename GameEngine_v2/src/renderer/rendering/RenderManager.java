package renderer.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import renderer.camera.ICamera;
import renderer.math.Vector2;
import renderer.math.Vector3;
import renderer.raycast.Raycast;
import renderer.raycast.RaycastManager;
import renderer.raycast.RaycastResult;
import renderer.visualizer.IDisplay;
import renderer.world.IWorld;

public class RenderManager {

	private static Random random = new Random();
	
	private static int[] randomColor() {
		int[] rgb = { random.nextInt(255), random.nextInt(255), random.nextInt(255) };
		return rgb;
	}
	
	public static BufferedImage generateRandomImage( IDisplay widget, IWorld world, ICamera camera ) {
		Vector2 imageResolution = camera.getResolution();
		BufferedImage newImage = new BufferedImage( (int) imageResolution.x, (int) imageResolution.y, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < imageResolution.x; x++ ) {
			for (int y = 0; y < imageResolution.y; y++ ) {
				int[] rgb = randomColor();
				newImage.setRGB(x, y, new Color(rgb[0], rgb[1], rgb[2]).getRGB() );
			}
		}
		return newImage;
	}
	
	public static Color getPixelColorFromRay( IWorld world, Vector3 origin, Vector3 direction ) {
		Color color = Color.BLACK;
		Raycast _raycast = new Raycast(origin, direction);
		RaycastResult result = RaycastManager.ResolveRaycast(world, _raycast);
		if (result != null && result.Triangle.color != null) {
			System.out.println(_raycast);
			System.out.println(result);
			color = result.Triangle.color;
		}
		return color;
	}
	
	public static BufferedImage generateImage( IWorld world, ICamera camera ) {
		
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
				Vector3 position = cameraPosition.copy().add(new Vector3(x, y, 0));
				Color color = getPixelColorFromRay(world, position, cameraDirection);
				// System.out.println(x + " || " + y + " || " + color.toString());
				newImage.setRGB(x, y, color.getRGB());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return newImage;
	}
}
