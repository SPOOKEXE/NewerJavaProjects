package renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import renderer.lib.Camera;
import renderer.lib.HitResult;
import renderer.lib.Ray;
import renderer.material.BaseMaterial;
import renderer.material.DielectricMaterial;
import renderer.material.LambertianMaterial;
import renderer.material.MaterialReflectionData;
import renderer.material.MetalMaterial;
import renderer.math.Utility;
import renderer.math.Vector3;
import renderer.primitives.PrimitiveHandler;
import renderer.primitives.Sphere;
import renderer.rendering.Pipeline;
import renderer.rendering.RendererData;

public class Main {

	static Vector3 COLOR_WHITE_V3 = new Vector3(1.0f, 1.0f, 1.0f);
    static Vector3 COLOR_LIGHT_BLUE_V3 = new Vector3(0.5f, 0.7f, 1.0f);
    
    public static Color resolveColor(float r, float g, float b, int samples_per_pixel) {
        // Replace NaN components with zero. See explanation in Ray Tracing: The Rest of Your Life.
        if (r == Float.NaN) r = 0.0f;
        if (g == Float.NaN) g = 0.0f;
        if (b == Float.NaN) b = 0.0f;

        // Divide the color by the number of samples and gamma-correct for gamma=2.0.
        float scale = 1.0f / samples_per_pixel;
        r = (float) Math.sqrt(scale * r);
        g = (float) Math.sqrt(scale * g);
        b = (float) Math.sqrt(scale * b);

        // Write the translated [0,255] value of each color component.
        return new Color( Utility.clamp(r, 0, 0.999f), Utility.clamp(g, 0, 0.999f), Utility.clamp(b, 0, 0.999f) );
    }
    
    public static Vector3 getRayColorVector( Ray ray, PrimitiveHandler world, int depth ) {
    	if (depth <= 0) return new Vector3();
    	
    	HitResult result = world.FindClosestIntersect(ray, 0.001f, Utility.INFINITY);
    	if (result != null) {
    		MaterialReflectionData reflectData = result.material.scatter(ray, result);
    		if (reflectData == null) return new Vector3();
    		return Main.getRayColorVector(reflectData.scattered, world, depth-1).mult(reflectData.attenuation);
    	}

		Vector3 unit_direction = ray.direction.unit();
		float t = 0.5f * (unit_direction.y + 1.0f);
		return Main.COLOR_LIGHT_BLUE_V3.mult(1.0f-t).add( Main.COLOR_WHITE_V3.mult(t) );
	}
    
    public static PrimitiveHandler randomScene() {
    	PrimitiveHandler world = new PrimitiveHandler();

        BaseMaterial ground_material = new LambertianMaterial(new Vector3(0.5f, 0.5f, 0.5f));
        world.add(new Sphere(new Vector3(0, -1000f, 0), 1000f, ground_material));

        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                float choose_mat = Utility.random_float();
                Vector3 center = new Vector3(a + 0.9f * Utility.random_float(), 0.2f, b + 0.9f * Utility.random_float());
                if ((center.sub(new Vector3(4f, 0.2f, 0))).mag() > 0.9f) {
                    BaseMaterial sphere_material;
                    if (choose_mat < 0.8f) {
                        // diffuse
                        Vector3 albedo = Utility.randomColorVec();
                        sphere_material = new LambertianMaterial(albedo);
                        world.add(new Sphere(center, 0.2f, sphere_material));
                    } else if (choose_mat < 0.95f) {
                        // metal
                    	Vector3 albedo = Utility.randomColorVec(0.5f, 1f);
                        float fuzz = Utility.random_float(0, 0.5f);
                        sphere_material = new MetalMaterial(albedo, fuzz);
                        world.add(new Sphere(center, 0.2f, sphere_material));
                    } else {
                        // glass
                        sphere_material = new DielectricMaterial(1.5f);
                        world.add(new Sphere(center, 0.2f, sphere_material));
                    }
                }
            }
        }

        BaseMaterial material1 = new DielectricMaterial(1.5f);
        world.add(new Sphere(new Vector3(0, 1, 0), 1.0f, material1));

        BaseMaterial material2 = new LambertianMaterial(new Vector3(0.4f, 0.2f, 0.1f));
        world.add(new Sphere(new Vector3(-4, 1, 0), 1.0f, material2));

        BaseMaterial material3 = new MetalMaterial(new Vector3(0.7f, 0.6, 0.5f), 0.0f);
        world.add(new Sphere(new Vector3(4, 1, 0), 1.0f, material3));

        return world;
    }
    
    public static PrimitiveHandler testScene() {
    	PrimitiveHandler world = new PrimitiveHandler(); 
        BaseMaterial material_ground = new LambertianMaterial( new Vector3(0.8f, 0.8f, 0.0f) );
        BaseMaterial material_center = new LambertianMaterial( new Vector3(0.1f, 0.2f, 0.5f) );
        BaseMaterial material_left = new DielectricMaterial( 1.5f );
        BaseMaterial material_right = new MetalMaterial( new Vector3(0.8f, 0.6f, 0.2f), 0.0f );
        world.add(new Sphere(new Vector3( 0.0, -100.5f, -1.0), 100.0f, material_ground));
        world.add(new Sphere(new Vector3( 0.0, 0.0, -1.0), 0.5f, material_center));
        world.add(new Sphere(new Vector3(-1.0, 0.0, -1.0), 0.5f, material_left));
        world.add(new Sphere(new Vector3(-1.0, 0.0, -1.0), -0.45f, material_left));
        world.add(new Sphere(new Vector3( 1.0, 0.0, -1.0), 0.5f, material_right));
        return world;
    }
    
    public static void main( String[] args ) {
    	
    	// Image
    	float aspect_ratio = 16.0f / 9.0f;
    	int image_width = 1280;
    	int image_height = (int) (image_width / aspect_ratio);

    	BufferedImage bufferImg = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_RGB);
    	
    	// Display
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        
        JProgressBar progressBar = new JProgressBar(0, image_width);
    	progressBar.setValue(0);
    	progressBar.setStringPainted(true);
        Component progressComp = frame.getContentPane().add(progressBar);
        
        JLabel image_label = new JLabel(new ImageIcon(bufferImg));
        frame.getContentPane().add(image_label);

        frame.pack();
        frame.setVisible(true);
        
    	// World
        PrimitiveHandler world = Main.randomScene();
        //PrimitiveHandler world = Main.testScene();
    	
    	// Camera
        Vector3 origin = new Vector3(16, 2.2f, 3.2f);
        Vector3 lookAt = new Vector3(0, 0, 0);
        Vector3 upVec = new Vector3(0, 1, 0);
        float dist_to_focus = 10.0f;
        float aperture = 0.1f;
        
    	Camera camera = new Camera(origin, lookAt, upVec, 20.0f, aspect_ratio, aperture, dist_to_focus);

    	// Render
    	int max_depth = 4;
    	int samples_per_pixel = 100;
    	
    	RendererData renderData = new RendererData( world, camera, samples_per_pixel, image_height, image_width, max_depth);
    	Color[][] pixel_columns = Pipeline.DistributeRender(renderData, progressBar, bufferImg, image_label);
    	for (int widthIndex = 0; widthIndex < image_width - 1; widthIndex++) {
    		Color[] columnPixels = pixel_columns[widthIndex];
    		for (int heightIndex = 0; heightIndex < image_height - 1; heightIndex++) {
    			Color pixel_color = columnPixels[heightIndex];
    			if (pixel_color == null) continue;
    			bufferImg.setRGB(widthIndex, heightIndex, pixel_color.getRGB());
    		}
    	}
    	
    	// preview in frame
        frame.getContentPane().remove( progressComp );
        frame.pack();
        
        // output image
        try {
            File outputfile = new File("saved_" + renderData.image_width + "_" + renderData.image_height + "_" + samples_per_pixel + "_" + max_depth + ".png");
            ImageIO.write(bufferImg, "png", outputfile);
            System.out.println("File saved at " + outputfile.getAbsolutePath());
        } catch (IOException e) {
        	System.out.println("Failed to save the image.");
        	e.printStackTrace();
        }
    }

}
