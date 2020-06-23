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
            System.out.println("Pressed: " + e.getCode().getName() + " (" + e.getCode() + ")");
            if (e.getEventType() == KeyEvent.KEY_PRESSED) {
                if (e.getCode() == KeyCode.K) {
                    // Move right when K is pressed
                    player.setDirection(90);
                    player.move();
                } else if (e.getCode() == KeyCode.J) {
                    // Move left when J is pressed
                    player.setDirection(270);
                    player.move();
                } else if (e.getCode() == KeyCode.F) {
                    // Shoot when F is pressed
                    System.out.println("Shoot!");
                }
                e.consume();
            } else if (e.getEventType() == KeyEvent.KEY_RELEASED
                    && (e.getCode() == KeyCode.J || e.getCode() == KeyCode.K)) {
                // Stop moving if J or K are released
                System.out.println("Stop Moving");
                player.stop();
                e.consume();
            }
        }

    };

    public EventHandler<KeyEvent> getKeyHandler() {
        return this.keyHandler;
    }

}
