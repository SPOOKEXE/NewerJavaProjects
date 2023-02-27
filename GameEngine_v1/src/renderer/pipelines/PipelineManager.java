package renderer.pipelines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import renderer.display.IDisplay;
import java.awt.*;

public class PipelineManager extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private Thread thread;
	
	private ArrayList<IDisplay> displays;
	
	private boolean running;
	private double start_ns;
	private double finish_ns;
	
	public PipelineManager() {
		System.out.println("Pipeline Manager");
		this.thread = new Thread("PipelineManagerThread");
		this.displays = new ArrayList<IDisplay>();
		this.start_ns = System.currentTimeMillis();
		this.finish_ns = -1;
	}

	public void init() {
		this.start();
	}
	
	public synchronized void start() {
		this.running = true;
		this.thread = new Thread(this, "PipelineManager");
		this.thread.start();
	}

	public synchronized void stop() {
		this.running = false;
		try {
			this.thread.interrupt();
		} catch (Exception ie) {
			ie.printStackTrace();
		}
	}
	
	public double getRunningTime() {
		return (
			this.finish_ns == -1 
			? System.currentTimeMillis() 
			: this.finish_ns
		) - this.start_ns;
	}
	
	public void setDisplays( ArrayList<IDisplay> displays ) {
		this.displays = displays;
	}
	
	public void update( ) {
		for (IDisplay display : this.displays) {
			display.update();
		}
	}
	
	public void render() {
		
		for (IDisplay display : this.displays) {
			
			JFrame container = display.getFrame();
			
			BufferStrategy bs = container.getBufferStrategy();
			if (bs == null) {
				container.createBufferStrategy(3); // triple buffering
				return;
			}
			
			Graphics g = bs.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);
			display.render(g);
			g.dispose();
			bs.show();
		}
		
	}

	@Override
	public void run() {
		
		double nansecond = 10e8 / 60; // 60 FPS
		double secondd = 1e3;
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int frames = 0;
		
		while (this.running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nansecond;
			lastTime = now;
			while (delta >= 1) {
				this.update();
				delta--;
				this.render();
				frames++;
			}
			if (System.currentTimeMillis() - timer > secondd) {
				System.out.println("reset");
				timer += secondd;
				int frameCount = frames;
				frames = 0;
				for (IDisplay display : this.displays) {
					display.setFPS(frameCount);
				}
			}
		}
		
	}
	
}
