package renderer.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import renderer.Main;
import renderer.lib.Ray;
import renderer.math.Utility;
import renderer.math.Vector3;

class ColumnRenderReturn {
	
	// Fields //
	Color[] colors;
	int widthIndex;
	
	// Constructors //
	public ColumnRenderReturn(Color[] colors, int widthIndex) {
		this.colors = colors;
		this.widthIndex = widthIndex;
	}
}

class ColumnRenderer implements Callable<ColumnRenderReturn> {
	
	// Fields //
	RendererData renderData;
	public int widthIndex;
	public Color colors[];
	
	// Constructors //
	public ColumnRenderer(RendererData renderData, int widthIndex) {
		this.renderData = renderData;
		this.widthIndex = widthIndex;
	}
	
	// Methods //
	@Override
	public ColumnRenderReturn call() {
		Color[] colors = new Color[renderData.image_height];
		for (int heightIndex = this.renderData.image_height - 1; heightIndex >= 0; --heightIndex) {
    		float[] rz = new float[this.renderData.samples_per_pixel];
    		float[] gz = new float[this.renderData.samples_per_pixel];
    		float[] bz = new float[this.renderData.samples_per_pixel];
    		for (int s = 0; s < this.renderData.samples_per_pixel; ++s) {
        		float u = (float) (this.widthIndex + Utility.random_float()) / (float)(this.renderData.image_width-1);
        		float v = (float) (heightIndex + Utility.random_float()) / (float)(this.renderData.image_height-1);
        		Ray ray = this.renderData.camera.getRay(u, v);
        		Vector3 rayColor = Main.getRayColorVector(ray, this.renderData.world, this.renderData.max_depth);
        		rz[s] = rayColor.x;
        		gz[s] = rayColor.y;
        		bz[s] = rayColor.z;
        	}
    		Color pixel_color = Main.resolveColor(
				Utility.sumFloats(rz),
				Utility.sumFloats(gz),
				Utility.sumFloats(bz), 
				this.renderData.samples_per_pixel
    		);
    		colors[heightIndex] = pixel_color;
    	}
		return new ColumnRenderReturn(colors, this.widthIndex);
	}
	
}

public class Pipeline {
	
	public static Color[][] DistributeRender(RendererData rendererData,  JProgressBar progressBar, BufferedImage image, JLabel image_label) {
		int total = 0;
		Color[][] colorz = new Color[rendererData.image_width][rendererData.image_height];
		
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println(availableProcessors);
		ExecutorService executor = Executors.newFixedThreadPool( availableProcessors - 1 );
		
		List< Future<ColumnRenderReturn> > futures = new ArrayList<Future<ColumnRenderReturn>>();
		System.out.println("Creating a total of " + (rendererData.image_width - 1) + " threads.");
		for (int widthIndex = 0; widthIndex <= rendererData.image_width - 1; ++widthIndex) {
			Callable<ColumnRenderReturn> task = new ColumnRenderer(rendererData, widthIndex);
       		Future<ColumnRenderReturn> submit = executor.submit(task);
    		futures.add(submit);
    		total += 1;
        }
		
		progressBar.setMaximum(total);
		executor.shutdown();
		
		int counter = 0;
		while (!executor.isTerminated()) {
			 for (Future<ColumnRenderReturn> future : futures) {
                try {
                	counter += 1;
                	progressBar.setValue( counter );
                	System.out.println("Task " + counter + " / " + total + " was completed.");
                	ColumnRenderReturn value = future.get();
                	for (int columnIndex = 0; columnIndex < value.colors.length - 1; columnIndex++) {
                		Color col = value.colors[columnIndex];
                		int yIndex = value.colors.length - 1 - columnIndex;
                		
                		image.setRGB(value.widthIndex, yIndex, col.getRGB());
                		colorz[value.widthIndex][yIndex] = col;
                	}
                	image_label.setIcon( new ImageIcon( image ) );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
	        try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
    	return colorz;
	}
	
}
