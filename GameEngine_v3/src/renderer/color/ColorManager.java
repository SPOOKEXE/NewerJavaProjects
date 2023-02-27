package renderer.color;

import java.awt.Color;

public class ColorManager {
	
	public static Color getShade(Color color, double shade) {
		double redLinear = Math.pow(color.getRed(), 2.4) * shade;
		double greenLinear = Math.pow(color.getGreen(), 2.4) * shade;
		double blueLinear = Math.pow(color.getBlue(), 2.4) * shade;

		int red = (int) Math.pow(redLinear, 1/2.4);
		int green = (int) Math.pow(greenLinear, 1/2.4);
		int blue = (int) Math.pow(blueLinear, 1/2.4);

		return new Color(red, green, blue);
	}
	
	public static Color mixAll( Color...colors ) {
		// just a little error checking
		if(colors == null || colors.length == 0){
			return null;
		}
		
		// calculate a concentration weight for a equal concentration of all colors in mix
		float concentration = (float) (1.0 / (1.0 + colors.length));
		
		// calculate first iteration
		float A_r = 1 * concentration;
		float A_g = 1 * concentration;
		float A_b = 1 * concentration;
		
		// sum the weighted average
		for(int i = 0; i < colors.length; i++){
			A_r += colors[i].getRed() * concentration;
			A_g += colors[i].getGreen() * concentration;
			A_b += colors[i].getBlue() * concentration;
		}
		
		// return with results
		return new Color(A_r, A_g, A_b);
	}
	
	public static Color mix( Color a, Color b) {
		return new Color(
			a.getRed() + b.getRed() / 2,
			a.getGreen() + b.getGreen() / 2,
			a.getBlue() + b.getBlue() / 2
		);
	}
	
}
