package de.tum.in.ase.eist;

public class Bullet extends UIElement implements MovingUIElement{

	public static String BULLET_IMAGE_FILE = "bullet.png";
	
	public Bullet(int x, int y) {
		super(x, y);
		this.MIN_SPEED = 4;
		this.MAX_SPEED = 5;
		this.setDirection(0);
		this.setIcon(BULLET_IMAGE_FILE);
	}

	@Override
	public void move() {
		

		
	}

}
