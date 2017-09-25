package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject {

	Random random = new Random();
	Handler handler;

	private BufferedImage player_image;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet sprite = new SpriteSheet(Game.sprite_sheet);
		
		player_image = sprite.grabImage(1, 1, 32, 32);
	}

	public void tick() {
		x += speedX;
		y += speedY;

		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision();

	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {   //tempObject is now a BasicEnemy or fastEnemy
				if (getBounds().intersects(tempObject.getBounds())) {
					// some collision code
					HUD.HEALTH -= 2;
				}
			}
		}
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.WHITE);
		//g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(player_image, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
