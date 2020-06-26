package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.List;

public class Invader extends UIElement implements ShootingUIElement {

	public static String INVADER_IMAGE_FILE = "Invader.png";
	private int bulletCount = 3;

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

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		bulletCount--;
	}
	
	public int getBulletCount() {
		return bulletCount;
	}

	public void setBulletCount(int bulletCount) {
		this.bulletCount = bulletCount;
	}
}
