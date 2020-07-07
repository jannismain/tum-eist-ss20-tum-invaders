package de.tum.in.ase.eist.view;

public class Invader extends UIElement {

	public static String INVADER_IMAGE_FILE = "Invader.png";

	/**
	 * Constructor for an Invader
	 *
	 * @param maxX Maximum x coordinate (width) of the game board
	 * @param maxY Maximum y coordinate (height) of the game board
	 */
	public Invader(int maxX, int maxY) {
		super(maxX, maxY);
		this.setIcon(INVADER_IMAGE_FILE);
	}

	public Bullet shoot() {
		return new Bullet(this.position.getX()+this.getSize().getWidth()/2, this.position.getY()-this.getSize().getHeight()/2, false);

	}
}
