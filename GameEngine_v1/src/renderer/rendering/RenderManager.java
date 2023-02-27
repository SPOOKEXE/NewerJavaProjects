package renderer.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import renderer.camera.ICamera;
import renderer.display.IDisplay;
import renderer.entity.IEntity;
import renderer.point.Point3D;
import renderer.point.Vector2;
import renderer.point.Vector3;
import renderer.raycaster.RaycastResult;
import renderer.raycaster.Raycaster;
import renderer.world.IWorld;

public class RenderManager {

	private static Random random = new Random();

	private static int[] randomColor() {
		int[] rgb = { random.nextInt(255), random.nextInt(255), random.nextInt(255) };
		return rgb;
	}
	
	public static BufferedImage generateRandomImage( IDisplay widget, IWorld world, ICamera camera ) {
		Vector2 imageResolution = camera.getResolution();
		BufferedImage newImage = new BufferedImage(imageResolution.x, imageResolution.y, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < imageResolution.x; x++ ) {
			for (int y = 0; y < imageResolution.y; y++ ) {
				int[] rgb = randomColor();
				newImage.setRGB(x, y, new Color(rgb[0], rgb[1], rgb[2]).getRGB() );
			}
		}
		return newImage;
	}
	
	public static BufferedImage generateImage( IDisplay widget, IWorld world, ICamera camera ) {
		Vector2 imageResolution = camera.getResolution();
		BufferedImage newImage = new BufferedImage(imageResolution.x, imageResolution.y, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < imageResolution.x; x++ ) {
			for (int y = 0; y < imageResolution.y; y++ ) {
				
				ArrayList<IEntity> entities = world.getEntities();
				Point3D cameraPosition = camera.getPosition();
				Vector3 cameraDirection = camera.getDirection();
				
				Color color = Color.BLACK;
				RaycastResult result = Raycaster.Raycast(entities, cameraPosition, cameraDirection);
				if (result != null) {
					color = result.color;
				}
				
				newImage.setRGB(x, y, color.getRGB());
			}
		}
		return newImage;
	}
	
}
