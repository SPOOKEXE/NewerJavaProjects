package renderer.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import renderer.math.Vector2;

public class Display implements IDisplay {

	private String type;

	private JFrame frame;

	private String title;
	private int FPS;
	private Vector2 displaySize;
	private boolean visible;

	private void setupFrame() {
		this.frame = new JFrame();
		this.frame.setTitle(title);
		Dimension size = new Dimension((int) this.displaySize.x, (int) this.displaySize.y);
		this.frame.setPreferredSize(size);
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop the program when the window is closed
		this.frame.setLocationRelativeTo(null); // Center to the middle of the screen
		this.frame.setResizable(false); // Disallow resizing
		this.frame.setVisible(this.visible); // Make it visible
	}

	public Display() {
		this.setSize(new Vector2(1280, 720));
		this.setTitle("Display");
		this.setVisible(true);
		this.setupFrame();
	}

	public Display(Vector2 size) {
		this.setSize(size);
		this.setTitle("Display");
		this.setVisible(true);
		this.setupFrame();
	}

	@Override
	public void setSize(Vector2 size) {
		this.displaySize = size;
		if (this.frame != null) {
			Dimension dimSize = new Dimension((int) size.x, (int) size.y);
			this.frame.setPreferredSize(dimSize);
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
	public void setVisible(boolean visible) {
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
		return (int) this.displaySize.x;
	}

	@Override
	public int getHeight() {
		return (int) this.displaySize.y;
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public void init() {
		this.frame.setTitle(this.title + " | " + this.FPS + " fps");
	}

	@Override
	public void update() {
		this.frame.setTitle(this.title + " | " + this.FPS + " fps");
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth() * 2, this.getHeight() * 2);
	}

	@Override
	public String toString() {
		String hashString = Integer.toHexString(this.hashCode());
		return String.format("%s | %s" + " | " + "Type: %s", this.type, hashString, this.type);
	}
	
	@Override
	public Graphics getGraphics() {
		return this.frame.getGraphics();
	}

}
