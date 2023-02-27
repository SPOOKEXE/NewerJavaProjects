package renderer.display;

import java.awt.Graphics;

import javax.swing.JFrame;

import renderer.math.Vector2;

public interface IDisplay {
	void init( );
	void update( );
	void render( Graphics g );
	
	void setSize( Vector2 size );
	void setTitle( String title );
	void setFPS( int FPS );
	void setVisible( boolean visible );
	
	JFrame getFrame();
	int getWidth( );
	int getHeight( );
	int getFPS();
	boolean isVisible( );
	String toString( );
	Graphics getGraphics();
}
