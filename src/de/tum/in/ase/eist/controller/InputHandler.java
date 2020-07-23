/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */
package de.tum.in.ase.eist.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import de.tum.in.ase.eist.view.*;

/**
 * This class is responsible for the handling the MOUSE_PRESSED Event, i.e. the
 * steering of the Player
 */

public class InputHandler {

    private Player player;
    private GameBoardUI gameBoardUI;

    /**
     * Constructor, creates InputHandler instance.
     *
     * @param gameBoardUI
     * @param player
     */
    public InputHandler(GameBoardUI gameBoardUI) {
        this.gameBoardUI = gameBoardUI;
        this.player = gameBoardUI.getGameBoard().getPlayer();
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
            if (e.getEventType() == KeyEvent.KEY_PRESSED) {
                if (e.getCode() == KeyCode.K) {
                    // Move right when K is pressed
                    player.setDirection(90);
                    player.setSpeed(10);
                } else if (e.getCode() == KeyCode.J) {
                    // Move left when J is pressed
                    player.setDirection(270);
                    player.setSpeed(10);
                } else if (e.getCode() == KeyCode.F) {
                    // Shoot when F is pressed
                    AbstractBullet b = player.shoot();
                    gameBoardUI.getGameBoard().getAudioPlayer().playShootSound();
                    // HACK: adding bullet to game
                    gameBoardUI.getGameBoard().addBullet(b, false);
                    gameBoardUI.UiImages.put(b, gameBoardUI.getImage(b.getIconLocation()));
                } else if (e.getCode() == KeyCode.D) {
                    player.switchBullet();
                }
                e.consume();
            } else if (e.getEventType() == KeyEvent.KEY_RELEASED
                    && (e.getCode() == KeyCode.J || e.getCode() == KeyCode.K)) {
                // Stop moving if J or K are released
                player.setSpeed(0);
                e.consume();
            }
        }

    };

    public EventHandler<KeyEvent> getKeyHandler() {
        return this.keyHandler;
    }

}
