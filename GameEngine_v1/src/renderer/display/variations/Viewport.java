package renderer.display.variations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import renderer.camera.ICamera;
import renderer.display.Display;
import renderer.display.IDisplay;
import renderer.rendering.RenderManager;
import renderer.world.IWorld;

public class Viewport extends Display implements IDisplay {

	@SuppressWarnings("unused")
	private String displayType = "_VIEWPORT";
	private BufferedImage bufferImage;
	
	private ICamera camera;
	private IWorld world;
	
	public Viewport(IWorld world, ICamera camera) {
		this.world = world;
		this.camera = camera;
		this.blankImage();
	}
	
	public Viewport() {
		this.blankImage();
	}
	
	public BufferedImage blankImage() {
		int sizeX = (this.getWidth() <= 0) ? 1 : this.getWidth();
		int sizeY = (this.getHeight() <= 0) ? 1 : this.getHeight();
		return new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
	}
	
	public void setCameraAndWorld(ICamera camera, IWorld world) {
		this.camera = camera;
		this.world = world;
	}
	
	@Override
	public void init() { 
		super.init();
	}

	@Override
	public void update() { super.update(); }
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		BufferedImage blankCanvas = this.blankImage();
		// BufferedImage pixels = RenderManager.generateRandomImage(this, world, camera);
		BufferedImage pixels = RenderManager.generateImage(this, this.world, this.camera);
		
		Graphics bGraphics = blankCanvas.getGraphics();
		// bGraphics.setColor(Color.WHITE);
		bGraphics.drawImage(pixels, 0, 0, this.getFrame());
		g.drawImage(blankCanvas, 0, 0, this.getFrame());
		this.bufferImage = blankCanvas;
	}
	
	public BufferedImage getBufferedImage() {
		return this.bufferImage;
	}

	@Override
	public void setSize(int sizeX, int sizeY) {
		super.setSize(sizeX, sizeY);
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
