package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.List;

public interface ShootingUIElement {
	
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public void shoot();

}
