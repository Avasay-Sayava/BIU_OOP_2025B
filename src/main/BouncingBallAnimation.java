// ID: 326552304

package main;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import util.ArrayUtils;
import util.Constants;
import util.DisgustingButYouSaidINeedToHaveItYey;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for the animation of a bouncing ball.
 */
public class BouncingBallAnimation {
    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] defaultArgs = {"", "", "", ""};
        System.arraycopy(args, 0, defaultArgs, 0, args.length);
        double[] doubleArgs = new double[4];
        ArrayUtils.toDouble(defaultArgs, doubleArgs);
        new Thread(() -> drawAnimation(new Point(doubleArgs[0], doubleArgs[1]), doubleArgs[2], doubleArgs[3])).start();
    }

    /**
     * Draws the animation of a bouncing ball.
     *
     * @param start the starting point of the ball
     * @param dx    the x velocity of the ball
     * @param dy    the y velocity of the ball
     */
    @DisgustingButYouSaidINeedToHaveItYey
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("BouncingBallAnimation", Constants.WIDTH, Constants.HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), Constants.BALL_RADIUS, Color.BLACK);
        ball.setVelocity(dx, dy);
        ArrayList<Line> lines = new ArrayList<>(List.of(
                new Rectangle(0, 0, Constants.WIDTH, Constants.HEIGHT).getLines()));
        while (true) {
            long time = System.currentTimeMillis();
            for (Line line : lines) {
                if (ball.isIntersecting(line)) {
                    ball.getCollider().collide(line);
                }
            }
            ball.getCollider().apply();
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            int elapsedTime = (int) (System.currentTimeMillis() - time);
            int sleepTime = (int) Math.round(1E3 / Constants.FPS) - elapsedTime;
            sleeper.sleepFor(Math.max(sleepTime, 0));
        }
    }
}
