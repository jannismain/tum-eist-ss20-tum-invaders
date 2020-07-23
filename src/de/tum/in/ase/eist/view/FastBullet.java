/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */
package de.tum.in.ase.eist.view;

public class FastBullet extends AbstractBullet {

	private int damage = 10;
	private int speed = 20;

	public FastBullet(int x, int y, boolean up) {
		super(x, y, up);
		this.setSpeed(speed);
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

}
