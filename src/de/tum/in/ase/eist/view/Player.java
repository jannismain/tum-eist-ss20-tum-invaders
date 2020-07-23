package de.tum.in.ase.eist.view;

/**
 * This class defines the player.
 *
 */
public class Player extends UIElement {

	public static String PLAYER_IMAGE_FILE = "Spaceship.png";
	public String bulletType = "default";

	/**
	 * Constructor for a Player object
	 */
	public Player() {
		this(225, 20);
	}

	public Player(int x, int y) {
		super(x, y);
		this.setIcon(PLAYER_IMAGE_FILE);
	}

	public AbstractBullet shoot() {
		int x = this.position.getX() + (this.getSize().getWidth() / 2) - 1;
		int y = this.position.getY() + this.getSize().getHeight() / 2;
		switch (bulletType) {
			case "fast":
				return new FastBullet(x, y, true);
			default:
				return new Bullet(x, y, true);
		}
	}

	public void switchBullet() {
		if (bulletType.equals("default")) {
			bulletType = "fast";
		} else {
			bulletType = "default";
		}
	}

}
