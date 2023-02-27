package renderer.visualizer.builder;

import renderer.camera.ICamera;
import renderer.visualizer.IDisplay;
import renderer.world.IWorld;

import renderer.visualizer.variations.Viewport;

public class BasicWidgets {

	public static IDisplay Viewport( ICamera camera, IWorld world ) {
		Viewport viewport = new Viewport(world, camera);
		viewport.setCameraAndWorld(camera, world);
		return viewport;
	}
	
}
