package de.tum.in.ase.eist;

public class Bullet extends UIElement implements MovingUIElement{

	public static String BULLET_IMAGE_FILE = "bullet.png";
	
	public Bullet(int x, int y) {
		super(x, y, 5 ,10);
		this.MIN_SPEED = 4;
		this.MAX_SPEED = 5;
		this.setDirection(0);
		this.setIcon(BULLET_IMAGE_FILE);
	}

	@Override
	public void move() {
		

		
	}
	
	public void updatePosition(int maxX, int maxY) {
	
		// calculate delta between old coordinates and new ones based on speed and direction
		float delta_x = this.getSpeed() * (float) Math.sin(Math.toRadians(getDirection()));
		float delta_y = this.getSpeed() * (float) Math.cos(Math.toRadians(getDirection()));

		// set coordinates
		this.position = new Point2D(this.position.getX() + delta_x, this.position.getY() + delta_y);

		// calculate position in case the boarder of the game board has been reached
		if (this.position.getX() < 0) {
			this.position = new Point2D(maxX - getSize().getWidth(), this.position.getY());// infinitely left loop
			setDirection(getDirection()); // doesnt change direction
		}

		if (this.position.getX() + getSize().getWidth() > maxX) {
			this.position = new Point2D(0, this.position.getY()); // infinitely right loop
			setDirection(getDirection()); // doesnt change direction
		}
		if (this.position.getY() - getSize().getHeight() < 0) {
			this.position = new Point2D(this.position.getX(), getSize().getHeight());
			int newDirection = 180 - getDirection();
			if (newDirection == 0)
			{
				reset(-1, -1); // it makes speed=. We are removing bullets with speed=0 in GameBoard.
			}
		}
		if (this.position.getY() > maxY) {
			this.position = new Point2D(this.position.getX(), maxY);
			int newDirection = 180 - getDirection();
			if (newDirection < 0) {
				setDirection(360 + newDirection);
			}
			else 
			{
				setDirection(newDirection);
			}
		}
	}

}
