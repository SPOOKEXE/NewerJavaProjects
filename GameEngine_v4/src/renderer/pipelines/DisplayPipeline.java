package renderer.pipelines;

import renderer.display.IDisplay;
import renderer.display.variations.Viewport;
import renderer.renderers.BaseRenderer;

public class DisplayPipeline {

	// Fields //
	BaseRenderer renderer;
	IDisplay display;
	
	// Constructor //
	public DisplayPipeline() {
		
	}
	
	// Class Methods //
	public void setRenderer(BaseRenderer renderer) {
		this.renderer = renderer;
	}
	
	public BaseRenderer getRenderer() {
		return this.renderer;
	}
	
	public void setDisplay(IDisplay display) {
		this.display = display;
	}
	
	public IDisplay getDisplay() {
		return this.display;
	}
	
	public void update() {
//		renderer.update();
//		if (this.display.getClass() == Viewport.class) {
//			((Viewport) this.display).setBufferedImage( );
//		}
	}
	
	// Static Methods //
	
}
