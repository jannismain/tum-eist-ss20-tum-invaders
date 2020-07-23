/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */
package de.tum.in.ase.eist.controller;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.ase.eist.controller.AudioPlayer.Sound;
import de.tum.in.ase.eist.view.*;
import de.tum.in.ase.eist.view.geometry.*;

/**
 * Creates all UIElements, detects collisions, updates UIElement positions,
 * notifies player about victory or defeat
 *
 */
public class GameBoard {

	private List<Invader> invaders = new ArrayList<>();
	private List<AbstractBullet> playerBullets = new ArrayList<>();
	private List<AbstractBullet> invaderBullets = new ArrayList<>();
	private Player player;

	private AudioPlayer audioPlayer;

	private Dimension2D size;

	// true if game is running, false if game is stopped
	private boolean isRunning;

	private boolean gameWon;

	public static int NUMBER_OF_INVADERS = 1;

	/**
	 * Constructor, creates the gameboard based on size
	 *
	 * @param size of the gameboard
	 */
	public GameBoard(Dimension2D size) {
		this.player = new Player();
		this.size = size;
		this.addInvaders();
	}

	/**
	 * Adds specified number of invaders to the invaders list.
	 */
	public void addInvaders() {
		for (int i = 0; i < NUMBER_OF_INVADERS; i++) {
			this.invaders.add(new Invader(this.size.getWidth(), this.size.getHeight()));
		}
	}

	/**
	 * Removes all existing UIElements, resets their position, creates new
	 * UIElements.
	 */
	public void resetElements() {
		this.player.reset(225, 20);
		this.invaders.clear();
		this.playerBullets.clear();
		this.invaderBullets.clear();
		addInvaders();
	}

	/**
	 * Checks if game is currently running by checking if the thread is running
	 *
	 * @return boolean isRunning
	 */
	public boolean isRunning() {
		return this.isRunning;
	}

	/**
	 * Used for testing only
	 *
	 * @param running true if game is still running, false otherwise
	 */
	public void setRunning(boolean running) {
		this.isRunning = running;
	}

	/**
	 *
	 * @return null if the game is running; true if the player has won; false if the
	 *         player has lost
	 */
	public boolean hasWon() {
		return this.gameWon;
	}

	/**
	 * @return list of invaders
	 */
	public List<Invader> getInvaders() {
		return this.invaders;
	}

	public List<AbstractBullet> getPlayerBullets() {
		return this.playerBullets;
	}

	public List<AbstractBullet> getInvaderBullets() {
		return this.invaderBullets;
	}

	public void addBullet(AbstractBullet b, boolean enemy) {
		if (enemy)
			this.invaderBullets.add(b);
		else
			this.playerBullets.add(b);
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * @return the gameboard's instance of AudioPlayer
	 */
	public AudioPlayer getAudioPlayer() {
		return this.audioPlayer;
	}

	public void setAudioPlayer(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	/**
	 * Starts the game.
	 */
	public void startGame() {
		// playMusic();
		this.isRunning = true;
	}

	/**
	 * Stops the game.
	 */
	public void stopGame() {
		// stopMusic();
		this.isRunning = false;
	}

	/**
	 * Starts the background music
	 */
	public void playMusic() {
		this.audioPlayer.playBackgroundMusic();
	}

	/**
	 * Stops the background music
	 */
	public void stopMusic() {
		this.audioPlayer.stopBackgroundMusic();
	}

	/**
	 * Iterate through list of UIElements and update their positions. Evaluate if
	 * either invader or player are hit by bullets.
	 */
	public void update() {

		List<AbstractBullet> bullets = getPlayerBullets();

		int maxX = (int) size.getWidth();
		int maxY = (int) size.getHeight();

		for (UIElement invader : getInvaders()) {
			invader.updatePosition(maxX, maxY);
			for (AbstractBullet bullet : bullets) {
				// iterate through all player bullets and see if enemy was shoot
				if (new Collision(invader, bullet).isCollision) {
					int remainingHealth = invader.damage(bullet.getDamage());
					if (remainingHealth <= 0) {
						audioPlayer.playSound(Sound.INVADER_KILLED);
						this.gameWon = true;
						this.stopGame();
					} else {
						audioPlayer.playSound(Sound.INVADER_HIT);
					}
				}
			}
		}

		player.updatePosition(maxX, maxY);

		for (UIElement bullet : bullets) {
			bullet.updatePosition(maxX, maxY);
		}

		for (UIElement bullet : getInvaderBullets()) {
			bullet.updatePosition(maxX, maxY);
			// iterate through all enemy bullets and see if player was shoot
			Collision collision = new Collision(player, bullet);
			if (collision.isCollision) {
				this.gameWon = false;
				this.stopGame();
				audioPlayer.playSound(Sound.EXPLOSION);
			}
		}
	}
}
