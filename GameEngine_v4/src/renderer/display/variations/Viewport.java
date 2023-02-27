package renderer.display.variations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import renderer.camera.ICamera;
import renderer.display.Display;
import renderer.display.IDisplay;
import renderer.math.Vector2;
import renderer.world.IWorld;

public class Viewport extends Display implements IDisplay {
	
	private BufferedImage bufferImage;
	
	public Viewport() {
		this.bufferImage = this.blankImage();
	}
	
	// CLASS METHODS //
	private BufferedImage blankImage() {
		int sizeX = (this.getWidth() <= 0) ? 1 : this.getWidth();
		int sizeY = (this.getHeight() <= 0) ? 1 : this.getHeight();
		return new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	public void init() { 
		super.init();
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		Graphics bGraphics = this.bufferImage.getGraphics();
		bGraphics.setColor(Color.BLACK);
		bGraphics.drawImage(this.bufferImage, 0, 0, this.getFrame());
		g.drawImage(this.bufferImage, 0, 0, this.getFrame());
	}
	
	public BufferedImage getBufferedImage() {
		return this.bufferImage;
	}
	
	@Override
	public void setSize(Vector2 viewportSize) {
		super.setSize(viewportSize);
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
	}

	@Override
	public void setFPS(int FPS) {
		super.setFPS(FPS);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	@Override
	public JFrame getFrame() {
		return super.getFrame();
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public int getFPS() {
		return super.getFPS();
	}

	@Override
	public boolean isVisible() {
		return super.isVisible();
	}
	
}
