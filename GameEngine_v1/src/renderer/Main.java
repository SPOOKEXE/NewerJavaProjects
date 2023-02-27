package renderer;

import renderer.input.UserInput;
import renderer.scene.IScene;
import renderer.scene.Scene;
import renderer.scene.SceneHandler;

public class Main {

	static SceneHandler mainScene;
	static UserInput userInput;
	
	public static void main( String[] args ) {
		System.out.println("Start Scene Machine");
		
		userInput = new UserInput();
		mainScene = new SceneHandler();
		mainScene.init();
		
		System.out.println("Generate Scene From File");
		IScene myScene = new Scene();
		myScene.loadScene("scene1");
		myScene.init();
	}

}
