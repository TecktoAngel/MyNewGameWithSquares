package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyHard extends GameObject{
	
	private Handler handler;
	private Random random = new Random();
	
	private BufferedImage enemy_image;
	
	public EnemyHard(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		speedX = 7;
		speedY = 7;
		
		SpriteSheet sprite = new SpriteSheet(Game.sprite_sheet);
		enemy_image = sprite.grabImage(1, 4, 16, 16);		
	}

	public void tick() {
		x += speedX;
		y += speedY;
	
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			if (speedY <= 0) {
				speedY = -(random.nextInt(7) + 1) * -1;
			} else {
				speedY = (random.nextInt(7) + 1) * -1;
			}
		}
		if (x <= 0 || x >= Game.WIDTH - 16) {
			if (speedX <= 0) {
				speedX = -(random.nextInt(7) + 1) * -1;
			} else {
				speedX = (random.nextInt(7) + 1) * -1;
			}
		}
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.YELLOW, 16, 16, 0.03f, handler));

	}

	public void render(Graphics graphics) {
		//graphics.setColor(Color.YELLOW);
		//graphics.fillRect((int)x, (int)y, 16, 16);
		graphics.drawImage(enemy_image, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x , (int)y, 16, 16);
	}
	
	

}
