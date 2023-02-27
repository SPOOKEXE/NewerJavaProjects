package renderer.pipeline;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import renderer.camera.ICamera;
import renderer.debug.PipelineDebugOutput;
import renderer.math.Vector2;
import renderer.world.IWorld;

public class PipelineManager {
	
	public static BufferedImage compileImage(IWorld world, ICamera camera, boolean multi_threaded) {
		Vector2 resolution = camera.getResolution();
		BufferedImage bufferedImage = new BufferedImage((int) resolution.x, (int) resolution.y, BufferedImage.TYPE_INT_ARGB);
		
		DefaultRenderBuffer renderBuffer = new DefaultRenderBuffer(world, camera);
		renderBuffer.drawEntitiesToImage(bufferedImage); // edits the image
		
		File outputfile = new File("image.png");
		try {
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(outputfile.getAbsoluteFile());
		
		PipelineDebugOutput.DumpToFile(true);
		System.out.println(PipelineDebugOutput.outputFile.getAbsolutePath());

		return bufferedImage;
	}
	
}
