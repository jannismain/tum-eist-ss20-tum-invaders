package de.tum.in.ase.eist;

public class Invader extends UIElement {

	public static String INVADER_IMAGE_FILE = "Invader.png";

	/**
	 * Constructor for a Invader
	 *
	 * @param maxX Maximum x coordinate (width) of the game board
	 * @param maxY Maximum y coordinate (height) of the game board
	 */
	public Invader(int maxX, int maxY) {
		super(maxX, maxY);
		this.MIN_SPEED = 2;
		this.MAX_SPEED = 5;
		this.setIcon(INVADER_IMAGE_FILE);
	}
}
