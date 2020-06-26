/**
 * This class was taken from Bumpers and adapted for Space Invader
 */
package de.tum.in.ase.eist;

/**
 * This class defines the behavior when two UIElements collide.
 */
public class Collision {

	protected UIElement element1;
	protected UIElement element2;
	public boolean isCollision;

	public Collision(UIElement element1, UIElement element2) {
		this.element1 = element1;
		this.element2 = element2;
		this.isCollision = detectCollision();
	}

	/**
	 * Checks whether two elements have collided. The translation transformation shifts
	 * a node from one place to another along one of the axes relative to its
	 * initial position.
	 *
	 * @return boolean - true if collision is detected.
	 */
	public boolean detectCollision() {
		Point2D p1 = element1.getPosition();
		Dimension2D d1 = element1.getSize();

		Point2D p2 = element2.getPosition();
		Dimension2D d2 = element2.getSize();

		if (p1.getY() + d1.getHeight() < p2.getY() || p1.getY() > p2.getY() + d2.getHeight())
			return false;

		return !(p1.getX() + d1.getWidth() < p2.getX()) && !(p1.getX() > p2.getX() + d2.getWidth());
	}
}
