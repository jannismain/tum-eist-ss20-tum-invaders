/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */

package de.tum.in.ase.eist;

import de.tum.in.ase.eist.view.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Starts the TumInvaders Application, loads the tool bar and the gameboardUI.
 * This class is the root of the JavaFX Application.
 */
public class TumInvadersApplication extends Application {

	public GameBoardUI gameBoardUI;
	public Toolbar toolBar; // the tool bar object with start and stop buttons

	/**
	 * Starts the TumInvaders Window by setting up a new tool bar, a new interface
	 * and adding them to the stage.
	 *
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * @param primaryStage the primary stage for this application, onto which the
	 *                     application scene can be set.
	 */
	@Override
	public void start(Stage primaryStage) {
		this.toolBar = new Toolbar(this);
		this.gameBoardUI = new GameBoardUI(this.toolBar);
		this.toolBar.resetToolBarButtonStatus(false); // set tool bar to default values

		// initialize GridPane and format
		// GridPanes are divided into columns and rows, like a table
		GridPane gridLayout = new GridPane();
		gridLayout.setPrefSize(505, 350);
		gridLayout.setVgap(5);
		gridLayout.setPadding(new Insets(5, 5, 5, 5));

		// add all components to the gridLayout
		// second parameter is column index, second parameter is row index of grid
		gridLayout.add(this.gameBoardUI, 0, 1);
		gridLayout.add(this.toolBar, 0, 0);

		// scene and stages
		Scene scene = new Scene(gridLayout);

		// Need to add keyHandler to the main scene
		scene.addEventHandler(KeyEvent.KEY_PRESSED, this.gameBoardUI.getInputHandler().getKeyHandler());
		scene.addEventHandler(KeyEvent.KEY_RELEASED, this.gameBoardUI.getInputHandler().getKeyHandler());
		primaryStage.setTitle("TumInvaders");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * The whole game will be executed through the launch() method
	 */
	public static void startApp(String[] args) {
		launch(args);
	}

}
