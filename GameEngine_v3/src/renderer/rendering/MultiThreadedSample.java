package renderer.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import renderer.camera.ICamera;
import renderer.math.Vector2;
import renderer.world.IWorld;

class PixelThread implements Runnable {
	public boolean completed = false;
	
	private IWorld world = null;
	private ICamera camera = null;
	public int columnNumber;
	public int height;
	
	public Color[] results = null;

	public boolean marked;
	public boolean started;
	
	public PixelThread(IWorld world, ICamera camera, int columnNumber, int height) {
		this.world = world;
		this.camera = camera;
		this.columnNumber = columnNumber;
		this.height = height;
	}

	@Override
	public void run() {
		this.started = true;
		// System.out.println("Starting thread #" + this.columnNumber);
		this.results = new Color[this.height];
		for (int height = 0; height < this.height; height++) {
			this.results[height] = Sampler.getPixelShadeColor(world, this.camera, new Vector2(this.columnNumber, height));
		}
		// System.out.println("Completed thread #" + this.columnNumber);
		this.completed = true;
	}
}

public class MultiThreadedSample {

	private static final int MAX_ACTIVE_THREADS = 200;

	public static BufferedImage generateImage(IWorld world, ICamera camera) {
		System.out.println(
			String.format("Generate Image with Multi-Threading! Position: %s, Direction: %s ",
			camera.getPosition(), camera.getCFrame().LookVector)
		);
		
		Vector2 imageResolution = camera.getResolution();
		
		int counter = 0;
		int active = 0;
		int pixels_count = (int) (imageResolution.x * imageResolution.y);
		int row_count = (int) imageResolution.x;
		
		// Lists for storing data
		HashMap<Integer, List<Color>> pixelsArray = new HashMap<Integer, List<Color>>(pixels_count);
		HashMap<Thread, PixelThread> pixelThreads = new HashMap<Thread, PixelThread>(row_count);
		
		// Load threads
		System.out.println("Starting " + row_count + " threads!");
		for (int x = 0; x < row_count; x++) {
			PixelThread classObj = new PixelThread(world, camera, x, (int) imageResolution.y);
			Thread threadObj = new Thread( classObj );
			// threadObj.start();
			pixelThreads.put(threadObj, classObj);
		}
		
		// While the counter is smaller than the total threads loaded, wait
		// java.util.ConcurrentModificationException
		double lastTick = 0;
		while (counter < row_count || active > 0) {
			double now = System.currentTimeMillis();
			if ((now - lastTick) > 1000) {
				System.out.println("Awaiting " + (row_count - counter) + " more threads to finish.");
				lastTick = now;
			}
			for (Thread threadObj : pixelThreads.keySet()) {
				PixelThread classObj = pixelThreads.get(threadObj);
				if (classObj.completed && !classObj.marked) {
					classObj.marked = true;
					//pixelThreads.remove(threadObj); // remove from hashmap
					pixelsArray.put(classObj.columnNumber, Arrays.asList(classObj.results) );
					counter += 1;
					active -= 1;
					break;
				} else if (!classObj.started && active < MAX_ACTIVE_THREADS) {
					active += 1;
					threadObj.start();
				}
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
		// Kill threads
		System.out.println("Kill Threads");
		for (Thread threadObj : pixelThreads.keySet()) {
			threadObj.interrupt();
		}
		
		// Compile the image
		BufferedImage newImage = new BufferedImage( (int) imageResolution.x, (int) imageResolution.y, BufferedImage.TYPE_INT_ARGB );
		for (int x : pixelsArray.keySet()) {
			List<Color> colorRow = pixelsArray.get(x);
			for (int y = 0; y < colorRow.size(); y++) {
				newImage.setRGB(x, y, colorRow.get(y).getRGB());
			}
		}
		
		return newImage;
	}

	
	
}
