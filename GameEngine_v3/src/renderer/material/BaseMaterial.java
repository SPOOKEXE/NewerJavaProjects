package renderer.material;

import java.awt.Color;

public class BaseMaterial implements IMaterial {
	public Color color;
	
	public boolean reflect;
	public float reflectFactor;
	
	public boolean diffuse;
	public float diffuseFactor;
	
	public boolean specular;
	public float specularFactor;
	
	// Constructors //
	public BaseMaterial() {
		this.toDefault();
	}
	
	public BaseMaterial(Color color) {
		this.toDefault();
		this.color = color;
	}
	
	// Methods //
	public void toDefault() {
		this.color = Color.BLACK;
		this.reflect = false;
		this.diffuse = false;
		this.specular = false;
		this.reflectFactor = 0;
		this.diffuseFactor = 0;
		this.specularFactor = 0;
	}
	
	public void scatter(Ray ray) {
		
	}
}
