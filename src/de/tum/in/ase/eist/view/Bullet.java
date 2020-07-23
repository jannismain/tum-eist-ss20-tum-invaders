/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */
package de.tum.in.ase.eist.view;

public class Bullet extends AbstractBullet {

	private int damage = 25;

	public Bullet(int x, int y, Boolean up) {
		super(x, y, up);
		this.setDirection(up ? 0 : 180);
		this.setIcon(BULLET_IMAGE_FILE);
	}

	@Override
	public int getDamage() {
		return this.damage;
	}

}
