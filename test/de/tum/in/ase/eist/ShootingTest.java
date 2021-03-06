package de.tum.in.ase.eist;

import org.junit.Test;
import static org.junit.Assert.*;
import de.tum.in.ase.eist.view.AbstractBullet;
import de.tum.in.ase.eist.view.Invader;
import de.tum.in.ase.eist.view.Player;

public class ShootingTest {

    @Test
    public void testBulletPositionPlayer() {
        Player player = new Player();
        AbstractBullet bullet = player.shoot();
        assertEquals(player.getPosition().getX() + (player.getSize().getWidth() / 2) - 1, bullet.getPosition().getX());
    }

    @Test
    public void testBulletPositionInvader() {
        Invader invader = new Invader(20, 250);
        AbstractBullet bullet = invader.shoot();
        assertEquals(invader.getPosition().getX() + (invader.getSize().getWidth() / 2), bullet.getPosition().getX());
    }

    @Test
    public void testInvaderBulletDown() {
        Invader invader = new Invader(20, 250);
        AbstractBullet bullet = invader.shoot();
        int downDirection = 180;
        assertEquals(bullet.getDirection(), downDirection);
    }

    @Test
    public void testPlayerBulletUp() {
        Player player = new Player();
        AbstractBullet bullet = player.shoot();
        int upDirection = 0;
        assertEquals(bullet.getDirection(), upDirection);
    }

}
