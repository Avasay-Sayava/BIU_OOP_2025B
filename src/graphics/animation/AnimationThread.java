package graphics.animation;

import biuoop.GUI;
import biuoop.Sleeper;

/**
 * This class represents a thread that runs an graphics.animation.
 * It is responsible for updating the graphics.animation and drawing it on the GUI.
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
     * Updates the graphics.animation.
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
     * Runs the graphics animation thread.
     */
    @Override
    public void run() {
        this.running = true;
        Sleeper sleeper = new Sleeper();
        while (this.running) {
            long time = System.currentTimeMillis();
            update();
            draw(this.gui);
            long elapsedTime = System.currentTimeMillis() - time;
            long sleepTime = Math.round(1E3 / this.fps - elapsedTime);
            sleeper.sleepFor(Math.max(sleepTime, 0));
        }
    }

    /**
     * Interrupts the graphics animation thread.
     */
    @Override
    public void interrupt() {
        this.running = false;
        super.interrupt();
    }
}
