package de.tum.in.ase.eist;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.UIElement;

/**
 * This class defines the behavior when two cars collide.
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
	 * Checks whether two cars have collided. The translation transformation shifts
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

	/**
	 * Evaluates winner of the collision
	 *
	 * @return winner Car
	 */
	public UIElement evaluate() {

		Point2D positionelement1 = this.element1.getPosition();
		Point2D positionelement2 = this.element2.getPosition();

		UIElement winnerCar = null;
		if (positionelement1.getX() < positionelement2.getX()) {
			winnerCar = this.element2;
		} else {
			winnerCar = this.element1;
		}
		return winnerCar;
	}

	/**
	 * Evaluates loser of the collision
	 *
	 * @return loser Car
	 */
	public UIElement evaluateLoser() {
		UIElement winner = this.evaluate();
		if (this.element1.equals(winner)) {
			return this.element2;
		}
		return this.element1;
	}
}
