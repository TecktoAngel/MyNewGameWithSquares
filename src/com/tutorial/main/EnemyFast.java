package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyFast extends GameObject{
	
	private Handler handler;
	
	private BufferedImage enemy_image;
	
	public EnemyFast(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		speedX = 2;
		speedY = 9;
		
		SpriteSheet sprite = new SpriteSheet(Game.sprite_sheet);
		enemy_image = sprite.grabImage(1, 3, 16, 16);
		
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
		
		//handler.addObject(new Trail(x, y, ID.Trail, enemy_image, 16, 16, 0.03f, handler));

	}

	public void render(Graphics graphics) {
		//graphics.setColor(Color.ORANGE);
		//graphics.fillRect((int)x, (int)y, 16, 16);
		graphics.drawImage(enemy_image, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	

}
