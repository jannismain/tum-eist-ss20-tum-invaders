/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */
package de.tum.in.ase.eist.view;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

import de.tum.in.ase.eist.SpaceInvaderApplication;

/**
 *
 * This class visualizes the tool bar with start, stop and exit buttons above
 * the game board.
 *
 */
public class Toolbar extends ToolBar {
    private SpaceInvaderApplication gameWindow;
    private Button startButton;
    private Button stopButton;

    public Toolbar(SpaceInvaderApplication gameWindow) {
        this.startButton = new Button("Start");
        this.stopButton = new Button("Stop");
        initActions();
        this.getItems().addAll(startButton, new Separator(), stopButton);
        this.setGameWindow(gameWindow);
    }

    /**
     * Initialises the actions
     */
    private void initActions() {
        this.startButton.setOnAction(event -> getGameWindow().gameBoardUI.startGame());

        this.stopButton.setOnAction(event -> {
            Toolbar.this.getGameWindow().gameBoardUI.stopGame();

            ButtonType YES = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType NO = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(AlertType.CONFIRMATION, "Do you really want to stop the game ?", YES, NO);
            alert.setTitle("Stop Game Confirmation");
            alert.setHeaderText("");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == YES) {
                getGameWindow().gameBoardUI.gameSetup();
            } else {
                getGameWindow().gameBoardUI.startGame();
            }
        });
    }

    /**
     * Resets the toolbar button status
     *
     * @param running Used to disable/enable buttons
     */
    public void resetToolBarButtonStatus(boolean running) {
        this.startButton.setDisable(running);
        this.stopButton.setDisable(!running);
    }

    /**
     * @return current gameWindow
     */
    public SpaceInvaderApplication getGameWindow() {
        return this.gameWindow;
    }

    /**
     * @param gameWindow New gameWindow to be set
     */
    public void setGameWindow(SpaceInvaderApplication gameWindow) {
        this.gameWindow = gameWindow;
    }
}
