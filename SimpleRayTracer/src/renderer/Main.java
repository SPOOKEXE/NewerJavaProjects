package renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import renderer.lib.Camera;
import renderer.lib.HitResult;
import renderer.lib.PrimitiveHandler;
import renderer.lib.Ray;
import renderer.lib.Sphere;
import renderer.material.BaseMaterial;
import renderer.material.DielectricMaterial;
import renderer.material.LambertianMaterial;
import renderer.material.MaterialReflectionData;
import renderer.material.MetalMaterial;
import renderer.math.Utility;
import renderer.math.Vector3;

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
        return new Color(
    		r < 0f ? 0f : (r > 0.999f ? 0.999f : r),
			g < 0f ? 0f : (g > 0.999f ? 0.999f : g),
			b < 0f ? 0f : (b > 0.999f ? 0.999f : b)
        );
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
        world.add(new Sphere(new Vector3(0, -1000, 0), 1000, ground_material));

        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                float choose_mat = Utility.random_float();
                Vector3 center = new Vector3(a + 0.9f * Utility.random_float(), 0.2, b + 0.9f * Utility.random_float());

                if ((center.sub(new Vector3(4, 0.2f, 0))).mag() > 0.9f) {
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
    	float aspect_ratio = 3.0f / 2.0f;
    	int image_width = 1200;
    	int image_height = (int) (image_width / aspect_ratio);
    	int samples_per_pixel = 500;
    	int max_depth = 50;
    	
    	BufferedImage bufferImg = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_RGB);
    	
    	// Display
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        
        JProgressBar progressBar = new JProgressBar(0, image_width * image_height);
    	progressBar.setValue(0);
    	progressBar.setStringPainted(true);
        Component progressComp = frame.getContentPane().add(progressBar);
        
        frame.pack();
        frame.setVisible(true);
        
    	// World
        PrimitiveHandler world = Main.randomScene();
        //PrimitiveHandler world = Main.testScene();
    	
    	// Camera
        Vector3 origin = new Vector3(13, 2, 3);
        Vector3 lookAt = new Vector3(0, 0, 0);
        Vector3 upVec = new Vector3(0, 1, 0);
        float dist_to_focus = 10.0f;
        float aperture = 0.1f;
        
    	Camera camera = new Camera(origin, lookAt, upVec, 20.0f, aspect_ratio, aperture, dist_to_focus);
    	
    	// Render
    	int counter = 0;
		for (int j = image_height - 1; j >= 0; --j) {
        	for (int i = 0; i <= image_width - 1; ++i) {
        		
        		// System.out.println(i + " - " + j);
        		counter += 1;
        		progressBar.setValue(counter);
        		
        		float r = 0.0f;
        		float g = 0.0f;
        		float b = 0.0f;
        		
        		for (int s = 0; s < samples_per_pixel; ++s) {
            		float u = (float) (i + Utility.random_float()) / (float)(image_width-1);
            		float v = (float) (j + Utility.random_float()) / (float)(image_height-1);
            		Ray ray = camera.getRay(u, v);
            		Vector3 rayColor = getRayColorVector(ray, world, max_depth);
            		r += rayColor.x;
            		g += rayColor.y;
            		b += rayColor.z;
            	}
        		
        		Color pixel_color = Main.resolveColor(r, g, b, samples_per_pixel);
        		bufferImg.setRGB(i, image_height-1-j, pixel_color.getRGB());
        	}
        }
        
        System.out.println("done");
        frame.getContentPane().remove( progressComp );
        frame.getContentPane().add(new JLabel(new ImageIcon(bufferImg)));
        frame.pack();
    }

}
