package com.tutorial.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Game game;

	private int scoreKeep = 0;

	private Random random = new Random();

	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void tick() {
		scoreKeep++;

		if (scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);

			if (game.diff == 0) {
				if (hud.getLevel() == 2) { // controlling enemies spawns
					handler.addObject(new EnemyBasic(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 3) {
					handler.addObject(new EnemyFast(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.addObject(new EnemySmart(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.addObject(new EnemySmart(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.addObject(new EnemyFast(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
					handler.addObject(new FirstBoss((Game.WIDTH / 2) - 32, -84, ID.FirstBoss, handler));
				}
			} else if (game.diff == 1) {
				if (hud.getLevel() == 2) { // controlling enemies spawns
					handler.addObject(new EnemyHard(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 3) {
					handler.addObject(new EnemyFast(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.addObject(new EnemySmart(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.addObject(new EnemySmart(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.addObject(new EnemyFast(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.FastEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
					handler.addObject(new FirstBoss((Game.WIDTH / 2) - 32, -84, ID.FirstBoss, handler));
				}
			}
		}
	}

}
