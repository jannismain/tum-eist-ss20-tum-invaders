/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */
package de.tum.in.ase.eist.view;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.List;

import de.tum.in.ase.eist.controller.*;
import de.tum.in.ase.eist.view.geometry.*;

/**
 * This class implements the user interface for controlling the player. The user
 * interface is implemented as a Thread that is started by clicking the start
 * button on the tool bar and stops by the stop button.
 *
 */
public class GameBoardUI extends Canvas implements Runnable {
	private static final Color backgroundColor = Color.BLACK;
	private static final int SLEEP_TIME = 1000 / 25; // this gives us 25fps
	private static final Dimension2D DEFAULT_SIZE = new Dimension2D(500, 300);
	// attribute inherited by the JavaFX Canvas class
	private GraphicsContext graphicsContext = this.getGraphicsContext2D();

	// thread responsible for starting game
	private Thread theThread;

	// user interface objects
	private GameBoard gameBoard;
	private Dimension2D size;
	private Toolbar toolBar;

	// user control objects
	private InputHandler keyboardSteering;

	public HashMap<UIElement, Image> UiImages;

	// invader shoot timer task
	private static Timer timer = new Timer();
	private static boolean shouldInvaderShoot = false;

	static class Task extends TimerTask {
		@Override
		public void run() {
			int delay = (1 + new Random().nextInt(4)) * 1000;
			timer.schedule(new Task(), delay);
			shouldInvaderShoot = true;
		}

	}

	// thread responsible for telling invaders to shoot
	private TimerTask invaderKillTimer = new Task();

	/**
	 * Sets up all attributes, starts the mouse steering and sets up all graphics
	 *
	 * @param toolBar used to start and stop the game
	 */
	public GameBoardUI(Toolbar toolBar) {
		this.toolBar = toolBar;
		this.size = getPreferredSize();
		gameSetup();
	}

	/**
	 * Called after starting the game thread Constantly updates the game board and
	 * renders graphics
	 *
	 * @see Runnable#run()
	 */
	@Override
	public void run() {
		while (this.gameBoard.isRunning()) {
			if (shouldInvaderShoot) {
				shouldInvaderShoot = false;
				List<Invader> invaders = gameBoard.getInvaders();
				for (Invader invader : invaders) {
					// HACK: instantiating enemy bullet here
					Bullet b = invader.shoot();
					gameBoard.addBullet(b, true);
					UiImages.put(b, getImage(b.getIconLocation()));
				}
			}
			// updates UIElements positions and re-renders graphics
			this.gameBoard.update();
			// when this.gameBoard.hasWon() is null, do nothing
			if (this.gameBoard.hasWon() == Boolean.FALSE) {
				showAsyncAlert("Oh.. you lost.");
				this.stopGame();
			} else if (this.gameBoard.hasWon() == Boolean.TRUE) {
				showAsyncAlert("Congratulations! You won!!");
				this.stopGame();
			}
			paint(this.graphicsContext);
			try {
				Thread.sleep(SLEEP_TIME); // milliseconds to sleep
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * @return current gameBoard
	 */
	public GameBoard getGameBoard() {
		return this.gameBoard;
	}

	/**
	 *
	 * @return input handler control object
	 */
	public InputHandler getInputHandler() {
		return this.keyboardSteering;
	}

	/**
	 * @return preferred gameBoard size
	 */
	public static Dimension2D getPreferredSize() {
		return DEFAULT_SIZE;
	}

	/**
	 * Resets all UIElements and renders graphics.
	 */
	public void gameSetup() {
		this.gameBoard = new GameBoard(this.size);
		this.gameBoard.setAudioPlayer(new AudioPlayer());
		this.widthProperty().set(this.size.getWidth());
		this.heightProperty().set(this.size.getHeight());
		this.size = new Dimension2D(getWidth(), getHeight());
		this.UiImages = new HashMap<>();
		this.keyboardSteering = new InputHandler(this);
		this.gameBoard.resetElements();
		this.gameBoard.getInvaders()
				.forEach((invader -> this.UiImages.put(invader, getImage(invader.getIconLocation()))));
		this.UiImages.put(this.gameBoard.getPlayer(), this.getImage(this.gameBoard.getPlayer().getIconLocation()));
		paint(this.graphicsContext);
		this.toolBar.resetToolBarButtonStatus(false);
	}

	/**
	 * Set and image
	 *
	 * @param ImageFilePath: an image file path that needs to be available in the
	 *                       resources folder of the project
	 * @return Image image at *ImageFilePath*
	 */
	public Image getImage(String ImageFilePath) {
		try {
			URL ImageUrl = getClass().getClassLoader().getResource(ImageFilePath);
			if (ImageUrl == null) {
				throw new RuntimeException(
						"Please ensure that your resources folder contains the appropriate files for this exercise.");
			}
			InputStream inputStream = ImageUrl.openStream();
			return new Image(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Starts the GameBoardUI Thread, if it wasn't running. Starts the game board,
	 * which causes the UIElements to move. Renders graphics and updates tool bar
	 * status.
	 */
	public void startGame() {
		if (!this.gameBoard.isRunning()) {
			this.gameBoard.startGame();
			this.theThread = new Thread(this);
			this.theThread.start();
			invaderKillTimer.run();
			paint(this.graphicsContext);
			this.toolBar.resetToolBarButtonStatus(true);
		}
	}

	/**
	 * Render the graphics of the whole game.
	 *
	 * @param graphics used to draw changes
	 */
	private void paint(GraphicsContext graphics) {
		graphics.setFill(backgroundColor);
		graphics.fillRect(0, 0, getWidth(), getHeight());

		for (UIElement invader : this.gameBoard.getInvaders()) {
			paintUIElement(invader, graphics);
		}
		// render player
		paintUIElement(this.gameBoard.getPlayer(), graphics);

		for (UIElement bullet : this.gameBoard.getPlayerBullets()) {
			paintUIElement(bullet, graphics);
		}

		for (UIElement bullet : this.gameBoard.getInvaderBullets()) {
			paintUIElement(bullet, graphics);
		}
	}

	/**
	 * Show image of a UIElement at its current position.
	 *
	 * @param element  to be drawn
	 * @param graphics used to draw changes
	 */
	private void paintUIElement(UIElement element, GraphicsContext graphics) {
		Point2D position = element.getPosition();
		Point2D canvasPosition = convertPosition(position);

		graphics.drawImage(this.UiImages.get(element), canvasPosition.getX(), canvasPosition.getY(),
				element.getSize().getWidth(), element.getSize().getHeight());
	}

	/**
	 * Converts position of UIElement to position on the canvas
	 *
	 * @param toConvert the point to be converted
	 * @return Point2D converted Point
	 */
	public Point2D convertPosition(Point2D toConvert) {
		return new Point2D(toConvert.getX(), getHeight() - toConvert.getY());
	}

	/**
	 * Stops the game board and set the tool bar to default values.
	 */
	public void stopGame() {
		if (this.gameBoard.isRunning()) {
			this.gameBoard.stopGame();
			timer.cancel();
			invaderKillTimer.cancel();
			this.toolBar.resetToolBarButtonStatus(false);
		}
	}

	public void showAsyncAlert(String message) {
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(message);
			alert.showAndWait();
			this.gameSetup();
		});
	}
}
