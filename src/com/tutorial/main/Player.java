package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{
	
	Random random = new Random();

	public Player(int x, int y, ID id) {
		super(x, y, id);
		
	}

	public void tick() {
		x += speedX;
		y += speedY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
	}

	public void render(Graphics g) {
		if(id == ID.Player) {
			g.setColor(Color.WHITE);
		}
		g.fillRect(x, y, 32, 32);
	}
	
	

}
