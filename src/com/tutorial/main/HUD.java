package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public int bounds = 0;	
	public static int HEALTH = 100; 
	private int greenValue = 255;
	
	private int score = 0;
	private int level = 1;

	public void tick() {
		HEALTH = (int) Game.clamp(HEALTH, 0, 100 + (bounds / 2));
		greenValue = HEALTH*2;
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		
		score++;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200 + bounds, 16);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH*2, 16);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200 + bounds, 16);
		
		g.drawString("Score: " + score, 15, 45);
		g.drawString("Level: " + level, 15, 60);
		g.drawString("Space for Shop", 15, 75);
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

}
