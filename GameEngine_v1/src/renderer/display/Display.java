package renderer.display;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Graphics;

public class Display implements IDisplay {
	
	private String displayType = "_DISPLAY";
	
	private JFrame frame;
	
	private int FPS;
	private String title;
	private boolean visible;
	private int sizeX;
	private int sizeY;
	
	private void setupFrame() {
		this.frame = new JFrame();
		this.frame.setTitle(title);
		Dimension size = new Dimension( this.sizeX, this.sizeY );
		this.frame.setPreferredSize( size );
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop the program when the window is closed
		this.frame.setLocationRelativeTo(null); // Center to the middle of the screen
		this.frame.setResizable(false); // Disallow resizing
		this.frame.setVisible(this.visible); // Make it visible
	}
	
	public Display() {
		this.setSize(400, 400);
		this.setTitle("Display");
		this.setVisible(true);
		this.setupFrame();
	}
	
	public Display( int sizeX, int sizeY ) {
		this.setSize( sizeX, sizeY );
		this.setTitle("Display");
		this.setVisible(true);
		this.setupFrame();
	}

	@Override
	public void setSize( int sizeX, int sizeY ) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		if (this.frame != null) {
			Dimension size = new Dimension( this.sizeX, this.sizeY );
			this.frame.setPreferredSize( size );
		}
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
		if (this.frame != null) {
			this.frame.setTitle(this.title);
		}
	}

	@Override
	public void setVisible( boolean visible ) {
		this.visible = visible;
		if (this.frame != null) {
			this.frame.setVisible(this.visible); 
		}
	}
	
	@Override
	public int getFPS() {
		return this.FPS;
	}

	@Override
	public void setFPS(int FPS) {
		this.FPS = FPS;
	}

	@Override
	public JFrame getFrame() {
		return this.frame;
	}
	
	@Override
	public int getWidth() {
		return this.sizeX;
	}

	@Override
	public int getHeight() {
		return this.sizeY;
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}
	
	@Override
	public void init( ) {
		this.frame.setTitle(this.title + " | " + this.FPS + " fps");
	}
	
	@Override
	public void update( ) {
		this.frame.setTitle(this.title + " | " + this.FPS + " fps");
	}

	@Override
	public void render( Graphics g ) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth() * 2, this.getHeight() * 2);
	}
	
	@Override
	public String toString() {
		
		return String.format(
			"%s | %s" + " | " + "Type: %s",
			this.displayType,
			Integer.toHexString(this.hashCode()),
			this.displayType
		);
	}
	
}
