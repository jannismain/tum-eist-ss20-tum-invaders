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
		this.setIcon(INVADER_IMAGE_FILE);
	}

	public Bullet shoot() {
		Bullet b = new Bullet(this.position.getX()+this.getSize().getWidth()/2, this.position.getY()-this.getSize().getHeight()/2);
		b.setDirection(180);
		return b;
	}
}
