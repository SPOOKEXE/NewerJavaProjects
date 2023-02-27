package renderer.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;

import renderer.camera.ICamera;
import renderer.math.CFrame;
import renderer.math.Vector2;
import renderer.world.IWorld;

public class SingleThreadedSample {
	
	// TODO: Change to rasterization method
	public static BufferedImage generateImage(IWorld world, ICamera camera) {
		
		// Create a blank image to write to
		Vector2 imageResolution = camera.getResolution();
		BufferedImage newImage = new BufferedImage(
			(int) imageResolution.x,
			(int) imageResolution.y,
			BufferedImage.TYPE_INT_ARGB
		);
		
		// Get pixel colors and change the BufferedImage
		for (int x = 0; x < imageResolution.x; x++ ) {
			for (int y = 0; y < imageResolution.y; y++ ) {
				// BaseMaterial material = Sampler.getPixelMaterial(world, pixelOffsetCF.Position, pixelOffsetCF.LookVector);
				// newImage.setRGB(x, y, material.color.getRGB());
				Color color = Sampler.getPixelShadeColor(world, camera, new Vector2(x, y));
				newImage.setRGB(x, y, color.getRGB());
				System.out.println(x + " || " + y); // + " || " + color.toString());
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				//break;
			}
			//break;
		}

		return newImage;
	}
	
}
