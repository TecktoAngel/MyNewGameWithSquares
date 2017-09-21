package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	
	private Handler handler;
	Random random = new Random();
	
	private Color color;
	
	int dir = 0;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		speedX = (random.nextInt(6 - -6) + -6);
		speedY = (random.nextInt(6 - -6) + -6);
		if (speedX == 0) {
			speedX = 1;
		}
		if (speedY == 0) {
			speedY = 1;
		}
		
		color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	public void tick() {
		x += speedX;
		y += speedY;
	
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			speedY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - 16) {
			speedX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.03f, handler));

	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x , (int)y, 16, 16);
	}
	
	

}
