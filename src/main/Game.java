package main;

import biuoop.DrawSurface;
import biuoop.GUI;
import collision.Collidable;
import collision.Colliding;
import graphics.animation.AnimationThread;
import graphics.Sprite;
import graphics.SpriteCollection;
import util.Constants;
import util.Counter;

import java.util.Comparator;

/**
 * A class that represents a game.
 */
public abstract class Game implements Runnable {
    private static int num = 0;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private AnimationThread animationThread;
    private GUI gui;
    private final Counter score;

    /**
     * Constructor.
     */
    public Game() {
        // Initialize the game environment.
        this.environment = new GameEnvironment();
        // Initialize the sprite collection.
        this.sprites = new SpriteCollection(spriteComparator());
        this.score = new Counter(0);
    }

    /**
     * @return the comparator to sort the sprites for drawing
     */
    public abstract Comparator<Sprite> spriteComparator();

    /**
     * Clears the game.
     */
    public void empty() {
        this.sprites.clear();
        this.environment.clear();
    }

    /**
     * @return the GUI of the game
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * @return the sprite collection
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Removes a collidable from the game.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a colliding from the game.
     *
     * @param c the colliding to remove
     */
    public void removeColliding(Colliding c) {
        this.environment.removeColliding(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Adds a collidable to the game.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a colliding to the game.
     *
     * @param c the colliding to add
     */
    public void addColliding(Colliding c) {
        this.environment.addColliding(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game.
     */
    public void initialize() {
        // Initialize the GUI.
        this.gui = new GUI(this.getClass().getSimpleName() + "-" + num++, Constants.WIDTH, Constants.HEIGHT);
        // Initialize the animation thread.
        this.animationThread = new AnimationThread(this.gui, Constants.FPS) {
            /**
             * Draws the frame on the GUI.
             *
             * @param gui the GUI to draw on
             */
            @Override
            public void draw(GUI gui) {
                DrawSurface d = gui.getDrawSurface();
                Game.this.sprites.drawAllOn(d);
                gui.show(d);
            }

            /**
             * Updates the graphics.animation.
             */
            @Override
            public void update() {
                Game.this.update();
            }
        };
        createAll();
    }

    /**
     * Updates the game each frame.
     */
    public void update() {
        this.environment.update();
        this.sprites.notifyAllTimePassed();
    }

    /**
     * Creates all the game objects.
     */
    public abstract void createAll();

    /**
     * Starts the game.
     */
    public void run() {
        this.animationThread.start();
    }

    /**
     * Stops the game.
     */
    public void stop() {
        this.animationThread.interrupt();
        this.gui.close();
    }

    /**
     * Recalculates the collisions in the game.
     * This method should be called when the game state changes and collisions need to be updated.
     */
    public abstract void recalculateCollisions();

    /**
     * @return the score of the game
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Ends the game.
     * This method should be called when the game is over, to perform any necessary cleanup or final actions.
     */
    public abstract void end();
}
