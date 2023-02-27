package renderer;

import renderer.math.Vector3;
import renderer.pipeline.PipelineManager;
import renderer.rendering.RenderManager;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import renderer.camera.Camera;
import renderer.camera.ICamera;
import renderer.debug.DebugOutput;
import renderer.debug.RenderingDebugOutput;
import renderer.entities.IEntity;
import renderer.material.BaseMaterial;
import renderer.math.Vector2;
import renderer.shapes.BasicEntityBuilder;
import renderer.visualizer.variations.Viewport;
import renderer.world.IWorld;
import renderer.world.World;

public class Main {

	// Output Pixel-by-Pixel Test Image //
	/**/
	public static void outputImage(Viewport display, IWorld world, ICamera camera, boolean multi_threaded) {
		BufferedImage bufferedImage = RenderManager.generateImage(world, camera, multi_threaded);
		File outputfile = new File("image.png");
		try {
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(outputfile.getAbsoluteFile());
		
		RenderingDebugOutput.DumpToFile(true);
		System.out.println(RenderingDebugOutput.outputFile.getAbsolutePath());
		
		display.getGraphics().drawImage(bufferedImage, 0, 0, display.getFrame());
	}
	
	// Output Rasterized Test Image//
	/*
	public static void outputImage(Viewport display, IWorld world, ICamera camera, boolean multi_threaded) {
		BufferedImage bufferedImage = PipelineManager.compileImage(world, camera, multi_threaded);
		display.getGraphics().drawImage(bufferedImage, 0, 0, display.getFrame());
	}*/
	
	public static void main(String[] args) {
		
		/*
		Triangle triangle = new Triangle(
			new Vector3( 1, 0, 0 ),
			new Vector3( 1, 1, 0 ),
			new Vector3( 0, 1, 0 )
		);
		
		Raycast _raycast = new Raycast( new Vector3(0.75, 0.75, -3), new Vector3(0, 0, 1) );
		IntersectResult result = IntersectManager.LineTriangleIntersect( triangle, _raycast.origin, _raycast.direction );
		System.out.println(result);
		DebugOutput.ResetFile();
		DebugOutput.DumpToFile();*/
		
		// Materials //
		/**/
			BaseMaterial blueMaterial = new BaseMaterial();
			blueMaterial.color = Color.BLUE;
			BaseMaterial redMaterial = new BaseMaterial();
			redMaterial.color = Color.RED;
			BaseMaterial orangeMaterial = new BaseMaterial();
			orangeMaterial.color = Color.ORANGE;
		 
		
		// Entities //
		/*
			IEntity cubeEntity = BasicEntityBuilder.createCube(5, new Vector3());
			cubeEntity.setMaterial(blueMaterial);
			System.out.println(cubeEntity);
		*/
		
		// Generate spheres //
		/**/
			BaseMaterial[] materials = new BaseMaterial[] { blueMaterial, redMaterial, orangeMaterial };
			ArrayList<IEntity> entities = new ArrayList<IEntity>();
			for (int x = -5; x < 5; x++) {
				for (int y = -5; y < 5; y++) {
					Vector3 pos = new Vector3(1 + (x * 2), 1 + (y * 2), 0);
					IEntity sphereEntity = BasicEntityBuilder.createSphere(3, 15, pos);
					sphereEntity.setMaterial(materials[new Random().nextInt(materials.length)]);
					entities.add(sphereEntity);
				}
			}
		
		
		/*
			IEntity sphereEntity = BasicEntityBuilder.createSphere(5, 15, new Vector3());
			sphereEntity.setMaterial(redMaterial);
			System.out.println(sphereEntity);
		*/
			
		/*
			BoundingBox bounds = BoundingBox.GetBoundingBoxForMeshes(sphereEntity.getMeshes());
			IEntity boundsCubeEntity = BasicEntityBuilder.createCube(5, new Vector3());
			boundsCubeEntity.setSize(bounds.size);
			boundsCubeEntity.setPosition(sphereEntity.getPosition());
			boundsCubeEntity.setMaterial(orangeMaterial);
			System.out.println(boundsCubeEntity);
			System.out.println(bounds.p0 + " - " + bounds.p1);
	
			IEntity sphereEntityLow = BasicEntityBuilder.createSphere(5, 8, new Vector3(-10, 0, 0));
			sphereEntityLow.setMaterial(orangeMaterial);
			System.out.println(sphereEntityLow);
	
			DebugOutput.DumpIEntity(sphereEntity);
			DebugOutput.DumpIEntity(boundsCubeEntity);
			
			DebugOutput.DumpIEntity(sphereEntityLow);
		*/
		
		// World //
			IWorld world = new World();
			world.addEntities(entities);
		/*
		 	world.addEntities(cubeEntity);
			world.addEntities(cubeEntity);
			world.addEntities(boundsCubeEntity);
			world.addEntities(sphereEntityLow);
		*/ 

		// Camera //
		/**/
			ICamera camera = new Camera("MAIN", new Vector3(10, 2, 10), new Vector3(0, 2, 0));
			// System.out.println(camera.getLookVector());
			camera.setResolution(new Vector2(1280, 720).mult((float) 0.5));
			System.out.println(camera.toString());
		
			
		// Display //	
		/**/
			Viewport display = new Viewport(world, camera);
			display.setVisible(true);
			world.addDisplays(display);

			Main.outputImage(display, world, camera, true);
	}
}
