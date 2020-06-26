package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.ase.eist.audio.AudioPlayerInterface;
import de.tum.in.ase.eist.UIElement;
import de.tum.in.ase.eist.Invader;
import de.tum.in.ase.eist.Collision;

/**
 * Creates all car objects, detects collisions, updates car positions, notifies
 * player about victory or defeat
 *
 */
public class GameBoard {

	private List<Invader> invaders = new ArrayList<>();
	private List<Bullet> bullets = new ArrayList<>();
	private Player player;

	private AudioPlayerInterface audioPlayer;

	private Dimension2D size;

	// true if game is running, false if game is stopped
	private boolean isRunning;

	private Boolean gameWon;

	public static int NUMBER_OF_INVADERS = 4;

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
	 * Adds specified number of cars to the cars list, creates new object for each
	 * car
	 */
	public void addUIElements() {
		for (int i = 0; i < NUMBER_OF_INVADERS; i++) {
			this.invaders.add(new Invader(this.size.getWidth(), this.size.getHeight()));
		}
	}

	/**
	 * Removes all existing cars from the car list, resets the position of the
	 * player car Invokes the creation of new car objects by calling addUIElements()
	 */
	public void resetElements() {
		this.player.reset(this.player.getPosition().getX(), this.player.getPosition().getY());
		this.invaders.clear();
		this.bullets.clear();
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
	 * @return list of cars
	 */
	public List<Invader> getInvaders() {
		return this.invaders;
	}
	
	public List<Bullet> getBullets() {
		return this.bullets;
	}

	/**
	 * @return the player's car
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
	 * Updates the position of each car
	 */
	public void update() {
		moveInvaders();
	}

	/**
	 * Starts the game. Cars start to move and background music starts to play.
	 */
	public void startGame() {
		// playMusic();
		this.isRunning = true;
	}

	/**
	 * Stops the game. Cars stop moving and background music stops playing.
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
	 * Iterate through list of cars (without the player car) and update each car's
	 * position Update player car afterwards separately
	 */
	public void moveInvaders() {

		List<Invader> invaders = getInvaders();
		List<Bullet> bullets = getBullets();

		// maximum x and y values a car can have depending on the size of the game board
		int maxX = (int) size.getWidth();
		int maxY = (int) size.getHeight();

		// update the positions of the player car and the autonomous cars
		for (UIElement invader : invaders) {
			invader.updatePosition(maxX, maxY);
		}
		
		for (UIElement bullet : bullets) {
			bullet.updatePosition(maxX, maxY);
		}
		
		// Bulletlar boardun dýþýna çýktýysa silelim.
		for(int i = bullets.size()-1; i >= 0; i--)
		{
			if (bullets.get(i).getSpeed() == 0)
				bullets.remove(i);
		}

		player.updatePosition(maxX, maxY);

		// iterate through all cars (except player car) and check if it is crunched
		for (UIElement invader : invaders) {

			// Create a new collision object
			// and check if the collision between player car and autonomous car evaluates as
			// expected
			Collision collision = new Collision(player, invader);

			if (collision.isCollision) {
				UIElement winner = collision.evaluate();
				UIElement loser = collision.evaluateLoser();
				System.out.println(winner);
				audioPlayer.playCrashSound();

				if (isWinner()) {
					this.gameWon = true;
					this.stopGame();
				} else if (this.getPlayer().equals(loser)) {
					this.gameWon = false;
					this.stopGame();
				}
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
