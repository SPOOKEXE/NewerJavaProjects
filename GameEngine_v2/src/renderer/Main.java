package renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import renderer.camera.Camera;
import renderer.camera.ICamera;
import renderer.entity.IEntity;
import renderer.math.Vector3;
import renderer.rendering.RenderManager;
import renderer.shapes.BasicEntityBuilder;
import renderer.visualizer.IDisplay;
import renderer.visualizer.variations.Viewport;
import renderer.world.IWorld;
import renderer.world.World;

public class Main {

	public static void main(String[] args) {
		// Entities //
		/**/
			IEntity cubeEntity = BasicEntityBuilder.createCube(10, new Vector3(), new Vector3(0, 1, 0));
			System.out.println(cubeEntity);
			IEntity sphereEntity = BasicEntityBuilder.createSphere(5, 6, new Vector3(), new Vector3(0, 1, 0));
			System.out.println(sphereEntity);
		
		
		// World //
		/**/
			IWorld world = new World();
			world.addEntities(sphereEntity);//, cubeEntity);
		
		
		// Camera //
		/**/
			ICamera camera = new Camera("MAIN", new Vector3(0, 0, 4), new Vector3(1, 0, 0));
			System.out.println(camera.toString());
		
		
		// Display //
		/**/
			IDisplay display = new Viewport(world, camera);
			display.setVisible(true);
			world.addDisplays(display);
		
		
		// Output Test Image //
		/**/
			BufferedImage bufferedImage = RenderManager.generateImage( world, camera );
			File outputfile = new File("image.png");
			try {
				ImageIO.write(bufferedImage, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(outputfile.getAbsoluteFile());
		
		
	}

}
