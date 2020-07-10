package de.tum.in.ase.eist;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import org.easymock.Mock;
import org.easymock.EasyMockRunner;
import org.easymock.TestSubject;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.tum.in.ase.eist.controller.weapons.*;

@RunWith(EasyMockRunner.class)
public class WeaponTest {

    @TestSubject
    EquippedWeapons ew = new EquippedWeapons();

    @Mock
    AbstractWeapon mockWeaponStrong;

    @Test
    public void testAddNewWeapon() {
        replay(mockWeaponStrong);
        ew.setEquippedWeapon(mockWeaponStrong);
        assertEquals(ew.getEquippedWeapon(), mockWeaponStrong);
    }

    @Test
    public void testGetEquippedWeaponRank() {
        expect(mockWeaponStrong.getRank()).andReturn(5);
        replay(mockWeaponStrong);
        ew.setEquippedWeapon(mockWeaponStrong);
        assertEquals(ew.getEquippedWeapon().getRank(), 5);
    }

    @Test
    public void testSelectWeapon() {
        expect(mockWeaponStrong.getRank()).andReturn(5);
        replay(mockWeaponStrong);

        AbstractWeapon defaultWeapon = new DefaultWeapon();
        ew.addWeapon(mockWeaponStrong);
        ew.addWeapon(defaultWeapon);
        ew.setEquippedWeapon(defaultWeapon);

        ew.selectWeapon();

        assertEquals(ew.getEquippedWeapon(), mockWeaponStrong);
    }
}
