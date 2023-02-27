package renderer.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneHandler {
	
	private List<IScene> scenes;
	
	public SceneHandler() {
		this.scenes = new ArrayList<IScene>();
	}

	public void init( ) {
		for (IScene scene : this.scenes) {
			scene.init();
		}
	}
	
	public void addScene( IScene scene ) {
		this.scenes.add( scene );
	}
	
}
