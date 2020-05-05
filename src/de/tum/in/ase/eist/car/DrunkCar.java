package de.tum.in.ase.eist.car;

public class DrunkCar extends Car {

	public static String DEFAULT_DRUNK_CAR_IMAGE_FILE = "DrunkCar.gif";
	public static int SPEED_CHANGE_INTERVAL = 20;

	private int update_counter = 0;

	/**
	 * Constructor for a DrunkCar.
	 *
	 * A drunk car will change its speed at random.
	 *
	 * @param maxX Maximum x coordinate (width) of the game board
	 * @param maxY Maximum y coordinate (height) of the game board
	 */
	public DrunkCar(int maxX, int maxY) {
		super(maxX, maxY);
		this.MIN_SPEED = 2;
		this.MAX_SPEED = 10;
		this.setRandomSpeed();
		this.setIcon(DEFAULT_DRUNK_CAR_IMAGE_FILE);
	}

	/**
	 * Calculates the new X and Y coordinations based on the current position,
	 * direction and speed
	 *
	 * @param maxX the current position along the x-axes
	 * @param maxY the current position along the y-axes
	 */
	public void updatePosition(int maxX, int maxY) {
		this.update_counter = (this.update_counter + 1) % SPEED_CHANGE_INTERVAL;
		if (this.update_counter == 0) {
			this.setRandomSpeed();
		}
		super.updatePosition(maxX, maxY);
	}
}
