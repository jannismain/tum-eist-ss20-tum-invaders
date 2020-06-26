/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */
package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.ase.eist.audio.AudioPlayerInterface;
import de.tum.in.ase.eist.UIElement;
import de.tum.in.ase.eist.Invader;
import de.tum.in.ase.eist.Collision;

/**
 * Creates all UIElements, detects collisions, updates UIElement positions, notifies
 * player about victory or defeat
 *
 */
public class GameBoard {

	private List<Invader> invaders = new ArrayList<>();
	private List<Bullet> bullets = new ArrayList<>();
	private List<Bullet> enemyBullets = new ArrayList<>();
	private Player player;

	private AudioPlayerInterface audioPlayer;

	private Dimension2D size;

	// true if game is running, false if game is stopped
	private boolean isRunning;

	private Boolean gameWon;

	public static int NUMBER_OF_INVADERS = 1;

	/**
	 * Constructor, creates the gameboard based on size
	 *
	 * @param size of the gameboard
	 */
	public GameBoard(Dimension2D size) {
		this.player = new Player();
		this.size = size;
		this.addUIElements();
	}

	/**
	 * Adds specified number of invaders to the invaders list.
	 */
	public void addUIElements() {
		for (int i = 0; i < NUMBER_OF_INVADERS; i++) {
			this.invaders.add(new Invader(this.size.getWidth(), this.size.getHeight()));
		}
	}

	/**
	 * Removes all existing UIElements, resets their position, creates new UIElements.
	 */
	public void resetElements() {
		this.player.reset(225, 20);
		this.invaders.clear();
		this.bullets.clear();
		this.enemyBullets.clear();
		addUIElements();
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
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 *
	 * @return null if the game is running; true if the player has won; false if the
	 *         player has lost
	 */
	public Boolean hasWon() {
		return this.gameWon;
	}

	/**
	 * @return list of invaders
	 */
	public List<Invader> getInvaders() {
		return this.invaders;
	}

	public List<Bullet> getBullets() {
		return this.bullets;
	}

	public List<Bullet> getEnemyBullets() {
		return this.enemyBullets;
	}

	public void addBullet(Bullet b, Boolean enemy) {
		if (enemy)
			this.enemyBullets.add(b);
		else
			this.bullets.add(b);
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
	public AudioPlayerInterface getAudioPlayer() {
		return this.audioPlayer;
	}

	public void setAudioPlayer(AudioPlayerInterface audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	/**
	 * Updates the position of each UIElement
	 */
	public void update() {
		updateUIElements();
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
		stopMusic();
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
	 * Iterate through list of UIElements and update their positions.
	 * Evaluate if either invader or player are hit by bullets.
	 */
	public void updateUIElements() {

		List<Invader> invaders = getInvaders();
		List<Bullet> bullets = getBullets();
		List<Bullet> enemyBullets = getEnemyBullets();

		int maxX = (int) size.getWidth();
		int maxY = (int) size.getHeight();

		for (UIElement invader : invaders) {
			invader.updatePosition(maxX, maxY);
			for (UIElement bullet : bullets) {
				// iterate through all player bullets and see if enemy was shoot
				if (new Collision(invader, bullet).isCollision) {
					this.gameWon = true;
					this.stopGame();
					audioPlayer.playInvaderKilledSound();
				}
			}
		}

		player.updatePosition(maxX, maxY);

		for (UIElement bullet : bullets) {
			bullet.updatePosition(maxX, maxY);
		}

		for (UIElement bullet : enemyBullets) {
			bullet.updatePosition(maxX, maxY);
			// iterate through all enemy bullets and see if player was shoot
			Collision collision = new Collision(player, bullet);
			if (collision.isCollision) {
				this.gameWon = false;
				this.stopGame();
				audioPlayer.playExplosionSound();
			}
		}
	}

	/**
	 * If all invaders are dead, the player wins.
	 *
	 * @return true if game is won
	 * @return false if game is not (yet) won
	 */
	private boolean isWinner() {
		if (getInvaders().size() == 0) {
			return true;
		}
		return false;
	}
}
