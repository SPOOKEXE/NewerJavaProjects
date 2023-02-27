package renderer.display;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;

public class DisplayManager extends Canvas {

	private static final long serialVersionUID = 1L;
	
	private List<IDisplay> displays;
	
	public DisplayManager() {
		System.out.println("Start Display Manager");
		this.displays = new ArrayList<IDisplay>();
	}
	
	public void init() {
		for (IDisplay display : this.displays) {
			display.init();
		}
	}
	
	public void update() {
		for (IDisplay display : this.displays) {
			display.update();
		}
	}
	
	public void addDisplay( IDisplay display ) {
		this.displays.add(display);
	}
	
	public List<IDisplay> getDisplays() {
		return this.displays;
	}
	
}
