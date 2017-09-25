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
	private HUD hud;

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent me) {
		int mouseX = me.getX();
		int mouseY = me.getY();

		if (game.gameState == STATE.Menu) {
			// play button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT, 200, 64)) {
				game.gameState = STATE.Select;
				AudioPlayer.getSound("sound").play();
				return;
			}
			// help button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 100, 200, 64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("sound").play();
			}
			// quit button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 200, 200, 64)) {
				AudioPlayer.getSound("sound").play();
				System.exit(1);
			}
		}
		// Select difficulty menu
		if (game.gameState == STATE.Select) {
			// Normal button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT, 200, 64)) {
				game.gameState = STATE.Game;
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new EnemyBasic(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy,
						handler));
				game.diff = 0;

				AudioPlayer.getSound("sound").play();
			}
			// Hard button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 100, 200, 64)) {
				game.gameState = STATE.Game;
				handler.clearEnemies();
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.addObject(new EnemyHard(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy,
						handler));
				game.diff = 1;

				AudioPlayer.getSound("sound").play();
			}
			// Back button
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 200, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("sound").play();
				return;
			}
		}
		// back button from help window
		if (game.gameState == STATE.Help) {
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 200, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("sound").play();
				return;
			}
		}

		// Try again button from end window
		if (game.gameState == STATE.End) {
			if (mouseOver(mouseX, mouseY, WIDTH, HEIGHT + 200, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				AudioPlayer.getSound("sound").play();
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
		} else if (game.gameState == STATE.End) {
			Font font = new Font("Jokerman", Font.BOLD, 50);
			Font font2 = new Font("Jokerman", Font.ITALIC, 30);

			graphics.setFont(font);
			graphics.setColor(Color.WHITE);
			graphics.drawString("Game Over", WIDTH - 35, HEIGHT - 35);

			graphics.setFont(font2);
			graphics.drawString("You lost! Your score: " + hud.getScore(), 130, 190);

			graphics.setFont(font2);
			graphics.drawRect(WIDTH, HEIGHT + 200, 200, 64);
			graphics.drawString("Try again", WIDTH + 25, HEIGHT + 242);
		} else if (game.gameState == STATE.Select) {
			Font font = new Font("Jokerman", Font.BOLD, 50);
			Font font2 = new Font("Jokerman", Font.ITALIC, 30);

			graphics.setFont(font);
			graphics.setColor(Color.WHITE);
			graphics.drawString("SELECT DIFFICULTY", 35, 70);

			graphics.setFont(font2);
			graphics.setColor(Color.WHITE);
			graphics.drawRect(WIDTH, HEIGHT, 200, 64);
			graphics.drawString("Normal", WIDTH + 40, HEIGHT + 42);

			graphics.setColor(Color.WHITE);
			graphics.drawRect(WIDTH, HEIGHT + 100, 200, 64);
			graphics.drawString("Hard", WIDTH + 60, HEIGHT + 142);

			graphics.setColor(Color.WHITE);
			graphics.drawRect(WIDTH, HEIGHT + 200, 200, 64);
			graphics.drawString("Back", WIDTH + 60, HEIGHT + 242);
		}

	}

	public void tick() {

	}

}
