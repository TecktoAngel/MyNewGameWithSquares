package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		this.game = game;

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for Player
				if (key == KeyEvent.VK_W) {
					tempObject.setSpeedY(-handler.speed);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setSpeedY(handler.speed);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setSpeedX(handler.speed);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setSpeedX(-handler.speed);
					keyDown[3] = true;
				}
			}
		}
		
		if (key == KeyEvent.VK_P) {
			if(game.gameState == STATE.Game) {
				if (Game.paused) {
					Game.paused = false;
				} else {
					Game.paused = true;
				}
			}
		}

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
		if (key == KeyEvent.VK_SPACE) {
			if (Game.gameState == STATE.Game) {
				Game.gameState = STATE.Shop;
			} else if (Game.gameState == STATE.Shop){
				Game.gameState = STATE.Game;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				// key events for Player
				if (key == KeyEvent.VK_W) {
					keyDown[0] = false;
					if (keyDown[1]) {
						tempObject.setSpeedY(5);
					}
				}
				if (key == KeyEvent.VK_S) {
					keyDown[1] = false;
					if (keyDown[0]) {
						tempObject.setSpeedY(-5);
					}
				}
				if (key == KeyEvent.VK_D) {
					keyDown[2] = false;
					if (keyDown[3]) {
						tempObject.setSpeedX(-5);
					}
				}
				if (key == KeyEvent.VK_A) {
					keyDown[3] = false;
					if (keyDown[2]) {
						tempObject.setSpeedX(5);
					}
				}

				// vertical movement
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setSpeedY(0);
				}
				// horizontal movement
				if (!keyDown[2] && !keyDown[3]) {
					tempObject.setSpeedX(0);
				}
			}
		}
	}
}
