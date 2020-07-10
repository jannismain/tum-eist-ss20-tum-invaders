package de.tum.in.ase.eist;
import static org.junit.Assert.*;

import de.tum.in.ase.eist.controller.Collision;

public class CollusionTest {
	
	public void collisionDetectionTest () {
		Collision coll= new Collision(null, null);
		boolean expected = true;
		boolean actual = coll.detectCollision();
		assertEquals(expected, actual);
		
		
	}

}
