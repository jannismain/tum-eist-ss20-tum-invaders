/**
 * This class was taken from Bumpers and adapted for SpaceInvaders
 */

package de.tum.in.ase.eist.view;

import de.tum.in.ase.eist.view.geometry.*;

/**
 * Abstract class for elements. Objects for this class cannot be instantiated.
 */
public abstract class UIElement {

    protected Point2D position;
    private Dimension2D size;
    private String iconLocation;

    // the direction is seen as degree within a circle
    private int direction = 90;
    public int speed = 10;

    /**
     * Constructor, taking x and y coordinates where UIElement shall be drawn.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public UIElement(int x, int y) {
        this(x, y, 50, 25);
    }

    /**
     * Special Constructor with custom width and height.
     */
    public UIElement(int x, int y, int width, int height) {
        this.position = new Point2D(x, y);
        this.size = new Dimension2D(width, height);
    }

    /**
     * The element's position is reset to (x,y). The speed is set to 0 and the
     * directions points to 90 degrees.
     */
    public void reset(int x, int y) {
        this.position = new Point2D(x, y);
        setDirection(90);
        this.speed = 0;
    }

    /**
     * Sets the element's direction
     *
     * @param direction
     * @throws IllegalArgumentException
     */
    public void setDirection(int direction) throws IllegalArgumentException {
        if (direction < 0 || direction > 360) {
            throw new IllegalArgumentException("Direction must be between 0 and 360");
        }
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getIconLocation() {
        return this.iconLocation;
    }

    /**
     * Sets the image of the UIElement
     *
     * @param iconLocation path of the image file
     * @throws IllegalArgumentException
     */
    protected void setIcon(String iconLocation) throws IllegalArgumentException {
        if (iconLocation == null) {
            throw new IllegalArgumentException("The icon cannot be null.");
        }
        this.iconLocation = iconLocation;
    }

    public Point2D getPosition() {
        return this.position;
    }

    /**
     * Sets the elements position
     *
     * @param x the position along the x-axes
     * @param y the position along the y-axes
     */
    public void setPosition(int x, int y) {
        this.position = new Point2D(x, y);
    }

    public Dimension2D getSize() {
        return this.size;
    }

    /**
     * Calculates the new X and Y coordinations based on the current position,
     * direction and speed
     *
     * @param maxX the current position along the x-axes
     * @param maxY the current position along the y-axes
     */
    public void updatePosition(int maxX, int maxY) {
        // calculate delta between old coordinates and new ones based on speed and
        // direction
        float delta_x = this.speed * (float) Math.sin(Math.toRadians(this.direction));
        float delta_y = this.speed * (float) Math.cos(Math.toRadians(this.direction));

        // set coordinates
        this.position = new Point2D(this.position.getX() + delta_x, this.position.getY() + delta_y);

        // calculate position in case the boarder of the game board has been reached
        if (this.position.getX() < 0) {
            this.position = new Point2D(0, this.position.getY());
            this.direction = 360 - this.direction;
        }

        if (this.position.getX() + this.size.getWidth() > maxX) {
            this.position = new Point2D(maxX - this.size.getWidth(), this.position.getY());
            this.direction = 360 - this.direction;
        }
        if (this.position.getY() - this.size.getHeight() < 0) {
            this.position = new Point2D(this.position.getX(), this.size.getHeight());
            this.direction = 180 - this.direction;
            if (this.direction < 0) {
                this.direction = 360 + this.direction;
            }
        }
        if (this.position.getY() > maxY) {
            this.position = new Point2D(this.position.getX(), maxY);
            this.direction = 180 - this.direction;
            if (this.direction < 0) {
                this.direction = 360 + this.direction;
            }
        }
    }

}
