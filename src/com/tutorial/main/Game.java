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
	private Spawn spawner;

	private Menu menu;

	public enum STATE {
		Menu, Game, Help
	};

	public STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler();
		menu = new Menu(this, handler);

		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);

		new Window(WIDTH, HEIGHT, "Square Warriors", this);

		hud = new HUD();
		spawner = new Spawn(handler, hud);
		random = new Random();

		if (gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			// Boss test spawn
			// handler.addObject(new FirstBoss((Game.WIDTH / 2) - 32, -84,
			// ID.FirstBoss, handler));
			handler.addObject(new BasicEnemy(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.BasicEnemy, handler));
		} else {
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}
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
		if (gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		} else if (gameState == STATE.Menu) {
			menu.tick();
		}
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
		graphics.fillRect((int) 0, (int) 0, WIDTH, HEIGHT);

		handler.render(graphics);

		if (gameState == STATE.Game) {
			hud.render(graphics);
		} else if (gameState == STATE.Menu || gameState == STATE.Help) {
			menu.render(graphics);
		}

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

	public static float clamp(float var, float min, float max) {
		if (var >= max) {
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
