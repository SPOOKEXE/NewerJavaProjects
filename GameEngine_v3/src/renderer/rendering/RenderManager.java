package renderer.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import renderer.camera.ICamera;
import renderer.math.Vector2;
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
	
	public static BufferedImage generateImage( IWorld world, ICamera camera, boolean multithreaded ) {
		if (multithreaded) {
			return MultiThreadedSample.generateImage(world, camera);
		}
		return SingleThreadedSample.generateImage(world, camera);
	}
	
	public static BufferedImage generateImage( IWorld world, ICamera camera) {
		return generateImage( world, camera, false);
	}
	
}
