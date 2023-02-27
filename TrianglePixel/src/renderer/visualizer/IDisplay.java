package renderer.visualizer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import renderer.math.Vector2;

public interface IDisplay {
	void init( );
	void update( );
	void render( Graphics g );
	void render_image( Graphics g, BufferedImage bfImg );
	
	void setSize(Vector2 size);
	void setTitle( String title );
	void setFPS( int FPS );
	void setVisible( boolean visible );
	
	JFrame getFrame();
	
	int getWidth( );
	int getHeight( );
	int getFPS();
	
	boolean isVisible( );
	
	String toString( );
}
