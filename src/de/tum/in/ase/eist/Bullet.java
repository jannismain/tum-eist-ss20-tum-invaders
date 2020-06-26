package de.tum.in.ase.eist;

public class Bullet extends UIElement implements MovingUIElement{

	public static String BULLET_IMAGE_FILE = "bullet.png";

	public Bullet(int x, int y) {
		super(x, y, 5 ,10);
		this.setDirection(0);
		this.setIcon(BULLET_IMAGE_FILE);
	}

	public void updatePosition(int maxX, int maxY) {

		// calculate delta between old coordinates and new ones based on speed and direction
		float delta_x = this.getSpeed() * (float) Math.sin(Math.toRadians(getDirection()));
		float delta_y = this.getSpeed() * (float) Math.cos(Math.toRadians(getDirection()));

		// set coordinates
		this.position = new Point2D(this.position.getX() + delta_x, this.position.getY() + delta_y);

		// calculate position in case the top or bottom boarder of the game board has been reached
		if (this.position.getY() - getSize().getHeight() < 0 || this.position.getY() > maxY) {
			// bullet has reached bottom || top
			this.reset(-1, -1);  // reset bullet will set speed to 0, bullet will be deleted on next update
		}
	}

}
