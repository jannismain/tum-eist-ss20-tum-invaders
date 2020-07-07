package de.tum.in.ase.eist;

import org.junit.Test;
import static org.junit.Assert.*;
import de.tum.in.ase.eist.view.Bullet;
import de.tum.in.ase.eist.view.Player;

public class ShootingTest {

    @Test
    public void testBulletPositionPlayer() {
        Player player = new Player();
        Bullet bullet = player.shoot();
        assertEquals(player.getPosition().getX()+(player.getSize().getWidth()/2)-1, bullet.getPosition().getX());
    }
}
