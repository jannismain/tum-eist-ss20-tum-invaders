package de.tum.in.ase.eist.controller;

import de.tum.in.ase.eist.UIElement;
import de.tum.in.ase.eist.Player;
import de.tum.in.ase.eist.gameview.GameBoardUI;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class is responsible for the handling the MOUSE_PRESSED Event, i.e. the
 * steering of the Player
 */

public class InputHandler {

    private UIElement player;
    private GameBoardUI gameBoardUI;

    /**
     * Constructor, creates InputHandler instance.
     *
     * @param gameBoardUI
     * @param player
     */
    public InputHandler(GameBoardUI gameBoardUI, Player player) {
        this.player = player;
        this.gameBoardUI = gameBoardUI;
        this.gameBoardUI.addEventHandler(KeyEvent.KEY_PRESSED, this.keyHandler);
        this.gameBoardUI.addEventHandler(KeyEvent.KEY_RELEASED, this.keyHandler);
    }

    public UIElement getPlayer() {
        return this.player;
    }

    public GameBoardUI getGameBoardUI() {
        return this.gameBoardUI;
    }

    public void setGameBoardUI(GameBoardUI gameBoardUI) {
        this.gameBoardUI = gameBoardUI;
    }

    EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent e) {
            // Show the help window when the F1 key is pressed
            System.out.println("Pressed: " + e.getCode().getName());
            if (e.getEventType() == KeyEvent.KEY_PRESSED) {
                player.move();
                if (e.getCode() == KeyCode.LEFT) {
                    player.setDirection(90);
                } else if (e.getCode() == KeyCode.RIGHT) {
                    player.setDirection(270);
                }
                e.consume();
            } else if (e.getEventType() == KeyEvent.KEY_RELEASED) {
                player.stop();
                e.consume();
            }
        }

    };

}
