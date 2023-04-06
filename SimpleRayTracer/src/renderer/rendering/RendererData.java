package renderer.rendering;

import renderer.lib.Camera;
import renderer.primitives.PrimitiveHandler;

public class RendererData {
	// Fields //
	public PrimitiveHandler world;
	public Camera camera;
	public int samples_per_pixel;
	public int image_height;
	public int image_width;
	public int max_depth;
	
	// Constructors //
	public RendererData(PrimitiveHandler world, Camera camera, int samples_per_pixel, int image_height, int image_width, int max_depth) {
		this.world = world;
		this.camera = camera;
		this.samples_per_pixel = samples_per_pixel;
		this.image_height = image_height;
		this.image_width = image_width;
		this.max_depth = max_depth;
	}
	
	// Methods //
	
}
