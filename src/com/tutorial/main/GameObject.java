package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float speedX, speedY;

	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	// Setters and Getters
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}
	
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	public float getSpeedX() {
		return speedX;
	}
	
	public float getSpeedY() {
		return speedY;
	}
}
