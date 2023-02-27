package renderer.display.builder;

import renderer.camera.ICamera;
import renderer.display.IDisplay;
import renderer.display.variations.Viewport;
import renderer.world.IWorld;

public class BasicWidgets {
	
	public static IDisplay Viewport( ICamera camera, IWorld world ) {
		Viewport viewport = new Viewport(world, camera);
		viewport.setCameraAndWorld(camera, world);
		return viewport;
	}
	
}
