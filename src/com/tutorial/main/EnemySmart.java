package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemySmart extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	public EnemySmart(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}		
	}

	public void tick() {
		x += speedX;
		y += speedY;
		
		//enemy AI forwards you
		if (player.getY() > y) { 
			speedY = 2;
		}
		if (player.getY() < y) { 
			speedY = -2;
		}
		if (player.getX() > x) { 
			speedX = 2;
		}
		if (player.getX() < x) { 
			speedX = -2;
		}
		
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			speedY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - 16) {
			speedX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.MAGENTA, 16, 16, 0.02f, handler));

	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.MAGENTA);
		graphics.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	

}
