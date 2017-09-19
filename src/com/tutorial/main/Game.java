package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;

	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean isRunning = false;

	private Random random;
	private Handler handler;
	
	private HUD hud;

	public Game() {
		handler = new Handler();

		this.addKeyListener(new KeyInput(handler));

		new Window(WIDTH, HEIGHT, "Da Game", this);
		
		hud = new HUD();

		random = new Random();

		handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player));
		handler.addObject(new BasicEnemy(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.BasicEnemy));
	}

	// running loop
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}

			if (isRunning)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
		hud.tick();

	}

	// render main window
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics graphics = bs.getDrawGraphics();

		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
	
		handler.render(graphics);

		hud.render(graphics);
		
		graphics.dispose();
		bs.show();
	}

	// starting a thread
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	// stopping a thread
	public synchronized void stop() {
		try {
			thread.join();
			isRunning = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int clamp(int var, int min, int max) {
		if(var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

	public static void main(String[] args) {
		new Game();
	}

}