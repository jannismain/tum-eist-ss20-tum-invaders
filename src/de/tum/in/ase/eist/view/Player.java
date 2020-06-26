package de.tum.in.ase.eist.view;

/**
 * This class defines the player.
 *
 */
public class Player extends UIElement {

	public static String PLAYER_IMAGE_FILE = "Spaceship.png";

	/**
	 * Constructor for a Player object
	 */
	public Player() {
		super(225, 20);
		this.setIcon(PLAYER_IMAGE_FILE);
	}

	public Bullet shoot() {
		return new Bullet(this.position.getX()+(this.getSize().getWidth()/2)-1, this.position.getY()+this.getSize().getHeight()/2, true);
	}

}
