package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		speedX = 5;
		speedY = 5;
		
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
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.03f, handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x , (int)y, 16, 16);
	}
	
	

}
