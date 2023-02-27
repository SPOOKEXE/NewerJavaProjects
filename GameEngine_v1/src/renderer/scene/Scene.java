package renderer.scene;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import renderer.display.IDisplay;
import renderer.os.OSOperations;
import renderer.pipelines.PipelineManager;
import renderer.world.IWorld;
import renderer.world.World;
import renderer.world.WorldManager;

public class Scene implements IScene {

	private WorldManager worldManager;
	private PipelineManager pipelineManager;
	
	public Scene() {
		this.worldManager = new WorldManager();
		this.pipelineManager = new PipelineManager();
	}
	
	public void loadScene( String sceneName ) {
		
		JSONObject sceneJSON = getSceneJSON( sceneName );
		if (sceneJSON == null) {
			System.out.println("No Scene named " + sceneName + " found.");
			return;
		}
		
		this.worldManager.clearWorlds();
		
		ArrayList<IWorld> generatedWorlds = new ArrayList<IWorld>();
		
		if ( sceneJSON.get("worlds") != null ) {
			JSONArray worldArray = (JSONArray) sceneJSON.get("worlds");
			for (Object worldJSON : worldArray ) {
				IWorld newWorld = new World();
				newWorld.loadWorld( (JSONObject) worldJSON );
				generatedWorlds.add(newWorld);
			}
		}
		
		this.worldManager.addWorlds( generatedWorlds );
		
		System.out.println("Generated Worlds: " + worldManager.countWorlds());
		for (IWorld world : worldManager.getWorlds()) {
			System.out.println("-- WORLD -- " + world.toString());
		}
		
		ArrayList<IDisplay> displayArray = new ArrayList<IDisplay>( this.worldManager.getDisplays() );
		System.out.println(displayArray);
		this.pipelineManager.setDisplays( displayArray );
	}
	
	public void init() {
		worldManager.init();
		pipelineManager.init();
	}	
	
	public static JSONObject getSceneJSON( String sceneName ) {

		String filePath = "./src/renderer/scenes/" + sceneName;
		FileReader jsonReader = OSOperations.getJSONFile( filePath );
		if (jsonReader == null) {
			System.out.println("Could not access JSON file.");
			return null;
		}
		
		System.out.println("Found File: " + filePath);
		
		// Load the JSON data
		JSONObject json = null;
		
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(jsonReader);
			json = (JSONObject) obj;
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        // } catch (ParseException e) {
        //    e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		// Close File Reader
		try {
			jsonReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Give JSON back
		return json;
	}
	
}
