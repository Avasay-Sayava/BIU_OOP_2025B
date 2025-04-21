package main;

import biuoop.GUI;
import biuoop.Sleeper;

/**
 * This class represents a thread that runs an animation.
 * It is responsible for updating the animation and drawing it on the GUI.
 */
public abstract class AnimationThread extends Thread {
    private static int id = 0;
    private final int fps;
    private final GUI gui;
    private boolean running;

    /**
     * Draws the frame on the GUI.
     *
     * @param gui the GUI to draw on
     */
    public abstract void draw(GUI gui);

    /**
     * Updates the animation.
     */
    public abstract void update();

    /**
     * Constructor.
     *
     * @param gui the GUI to draw on
     * @param fps the frames per second
     */
    public AnimationThread(GUI gui, int fps) {
        super("AnimationThread-" + id++);
        this.fps = fps;
        this.gui = gui;
    }

    /**
     * Runs the animation thread.
     */
    @Override
    public void run() {
        this.running = true;
        while (this.running) {
            long time = System.currentTimeMillis();
            update();
            draw(this.gui);
            long elapsedTime = System.currentTimeMillis() - time;
            long sleepTime = Math.round(1E3 / this.fps - elapsedTime);
            new Sleeper().sleepFor(Math.max(sleepTime, 0));
        }
    }

    /**
     * Interrupts the animation thread.
     */
    @Override
    public void interrupt() {
        this.running = false;
        super.interrupt();
    }
}
