package renderer.display;

import java.awt.Graphics;

import javax.swing.JFrame;

public interface IDisplay {
	
	void init( );
	void update( );
	void render( Graphics g );
	
	void setSize( int sizeX, int sizeY );
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
