package de.tum.in.ase.eist;

import static org.junit.Assert.*;

import de.tum.in.ase.eist.view.Player;
import de.tum.in.ase.eist.controller.Collision;

public class CollusionTest {

	@Test
	public void testCollisionDetectionTrue() {
		Player player = new Player(250, 250);
		Collision coll = new Collision(player, player);
		assertTrue(coll.detectCollision());
	}

	@Test
	public void testCollisionDetectionFalse() {
		Player player1 = new Player(250, 250);
		Player player2 = new Player(400, 400);
		Collision coll = new Collision(player1, player2);
		assertFalse(coll.detectCollision());
	}

}
