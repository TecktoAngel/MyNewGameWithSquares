package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FirstBoss extends GameObject {

	private Handler handler;

	private int timer = 140;
	private int timer2 = 30;
	
	Random random = new Random();

	public FirstBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		speedX = 0;
		speedY = 2;

	}

	public void tick() {
		x += speedX;
		y += speedY;

		timer--;

		if (timer <= 0) {
			speedY = 0;
		} else {
			timer--;
		}

		if (timer <= 0) {
			timer2--;
		}
		if (timer2 <= 0) {
			if (speedX == 0) {
				speedX = 2;				
			}
		}
		
		//Speed increasing for the boss movement
		if (speedX > 0) {
			speedX += 0.005f;
		} else {
			speedX -= 0.005f;
		}
		
		speedX = Game.clamp(speedX, -10, 10);
		
		int spawn = random.nextInt(10);
		if (spawn == 0) {
			handler.addObject(new FirstBossShoots((int)x+32, (int)y+32, ID.BasicEnemy, handler));
		}
		
		/*
		 * if(y <= 0 || y >= Game.HEIGHT - 64) { speedY *= -1; }
		 */
		if (x <= 0 || x >= Game.WIDTH - 64) {
			speedX *= -1;
		}
		
		// Trail for the boss (optional)
		// handler.addObject(new Trail(x, y, ID.Trail, Color.PINK, 64, 64,
		// 0.009f, handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, 64, 64);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}

}
