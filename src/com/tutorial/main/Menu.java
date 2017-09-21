package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {

	private final static int HEIGHT = 100;
	private final static int WIDTH = Game.WIDTH / 2 - HEIGHT;

	private Random random = new Random();

	private Game game;
	private Handler handler;

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent me) {
		int mouseX = me.getX();
		int mouseY = me.getY();

		if (game.gameState == STATE.Menu) {
			// play button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy,
						handler));
			}
			// help button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 100, 200, 64)) {
				game.gameState = STATE.Help;
			}
			// quit button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 200, 200, 64)) {
				System.exit(1);
			}
		}
		// back button from help window
		if (game.gameState == STATE.Help) {
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 200, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
	}

	public void mouseReleased(MouseEvent me) {

	}

	private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if (mouseX > x && mouseX < x + width) {
			if (mouseY > y && mouseY < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void render(Graphics graphics) {
		if (game.gameState == STATE.Menu) {
			Font font = new Font("Jokerman", Font.BOLD, 50);
			Font font2 = new Font("Jokerman", Font.ITALIC, 30);
			// Title Menu
			graphics.setFont(font);
			graphics.setColor(Color.WHITE);
			graphics.drawString("Menu", WIDTH + 35, HEIGHT - 35);
			// First block of menu
			graphics.setFont(font2);
			graphics.setColor(Color.WHITE);
			graphics.drawRect(WIDTH, HEIGHT, 200, 64);
			graphics.drawString("Play", WIDTH + 70, HEIGHT + 42);
			// Second block of menu
			graphics.setColor(Color.WHITE);
			graphics.drawRect(WIDTH, HEIGHT + 100, 200, 64);
			graphics.drawString("Help", WIDTH + 65, HEIGHT + 142);
			// Third block of menu
			graphics.setColor(Color.WHITE);
			graphics.drawRect(WIDTH, HEIGHT + 200, 200, 64);
			graphics.drawString("Quit", WIDTH + 65, HEIGHT + 242);
		} else if (game.gameState == STATE.Help) {
			Font font = new Font("Jokerman", Font.BOLD, 50);
			Font font2 = new Font("Jokerman", Font.ITALIC, 30);

			graphics.setFont(font);
			graphics.setColor(Color.WHITE);
			graphics.drawString("Help", WIDTH + 35, HEIGHT - 35);

			graphics.setFont(font2);
			graphics.drawString("Use WASD to move and avoid the enemies!", 10, 150);
			graphics.drawString("Have Fun!", 10, 190);

			graphics.setFont(font2);
			graphics.drawRect(WIDTH, HEIGHT + 200, 200, 64);
			graphics.drawString("Back", WIDTH + 65, HEIGHT + 242);
		}

	}

	public void tick() {

	}

}
