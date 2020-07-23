package de.tum.in.ase.eist;

import org.junit.Test;
import static org.junit.Assert.*;
import de.tum.in.ase.eist.controller.GameBoard;

import de.tum.in.ase.eist.view.geometry.Dimension2D;

public class GameBoardTest {

    @Test
    public void testGameboardInit() {
        GameBoard gameboard = new GameBoard(new Dimension2D(500, 500));
        assertEquals(gameboard.getBullets().isEmpty(), true);
    }

}
