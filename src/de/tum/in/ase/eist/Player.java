package de.tum.in.ase.eist;

import de.tum.in.ase.eist.UIElement;

/**
 * This class defines the player. Each player has its own UIElement.
 *
 */
public class Player extends UIElement {

	public static String PLAYER_IMAGE_FILE = "Spaceship.png";

	/**
	 * Constructor that allocates a car to the player
	 *
	 * @param car the car that should be the player's car
	 */
	public Player() {
		super(225, 20);
		this.setIcon(PLAYER_IMAGE_FILE);
	}

	public Bullet shoot() {
		return new Bullet(this.position.getX()+(this.getSize().getWidth()/2)-1, this.position.getY()+this.getSize().getHeight()/2, true);
	}

}
