package de.tum.in.ase.eist;

import de.tum.in.ase.eist.UIElement;

/**
 * This class defines the player. Each player has its own UIElement.
 *
 */
public class Player extends UIElement {

	public static String PLAYER_IMAGE_FILE = "Spaceship.png";

	private UIElement element;

	/**
	 * Constructor that allocates a car to the player
	 *
	 * @param car the car that should be the player's car
	 */
	public Player(UIElement element) {
		super(225, 20);
		this.element = element;
		this.setIcon(PLAYER_IMAGE_FILE);
	}

	/**
	 * @param car the player's new car
	 */
	public void setElement(UIElement element) {
		this.element = element;
	}

	/**
	 * @return The player's current car
	 */
	public UIElement getElement() {
		return this.element;
	}

}
