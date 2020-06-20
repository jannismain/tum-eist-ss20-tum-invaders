package de.tum.in.ase.eist;

public class Barrier extends UIElement {

	public static String BARRIER_IMAGE_FILE = "Barrier.gif";

	/**
	 * Constructor for a Barrier.
	 *
	 * @param maxX x coordinate of the barrier
	 * @param maxY y coordinate of the barrier
	 */
	public Barrier(int x, int y) {
		super(x, y);
		this.setIcon(BARRIER_IMAGE_FILE);
	}
}
