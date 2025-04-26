// ID: 326552304

package main;

import biuoop.DrawSurface;
import biuoop.GUI;
import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import graphics.Drawable;
import physics.Velocity;
import util.ArrayUtils;
import util.BallUtils;
import util.Constants;
import util.MathUtils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * A class for the animation of multiple bouncing balls with two frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] intArgs = new int[args.length];
        ArrayUtils.toInteger(args, intArgs);
        ArrayUtils.shuffleInt(intArgs);
        for (int i = 0; i < intArgs.length; i++) {
            intArgs[i] = Math.max(Math.min(intArgs[i], 100), 5);
        }

        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", Constants.WIDTH, Constants.HEIGHT);
        Rectangle rect1 = new Rectangle(450, 450, 600, 600);
        Rectangle rect2 = new Rectangle(50, 50, 500, 500);
        Drawable[] foreground = {rect1};
        Drawable[] background = {rect2};
        ArrayList<Line> lines = new ArrayList<>(List.of(
                new Rectangle(0, 0, Constants.WIDTH, Constants.HEIGHT).getLines()));
        lines.addAll(List.of(rect1.getLines()));
        lines.addAll(List.of(rect2.getLines()));

        Ball[] balls = new Ball[intArgs.length];
        for (int i = 0; i < intArgs.length; i++) {
            Point center = generateCenter(intArgs, rect1, rect2, i);
            Color color = Color.getHSBColor(MathUtils.randomFloat((float) i / intArgs.length,
                    (float) (i + 1) / intArgs.length), 1, 1);
            balls[i] = new Ball(center, intArgs[i], color);
            double speed = BallUtils.getSpeed(intArgs[i]);
            double angle = MathUtils.randomDouble(2 * Math.PI);
            balls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }
        Arrays.sort(balls, Comparator.comparingDouble(Ball::getRadius).reversed());

        new AnimationThread(gui, Constants.FPS) {
            /**
             * Draws the frame.
             *
             * @param gui the GUI to draw on
             */
            @Override
            public void draw(GUI gui) {
                DrawSurface d = gui.getDrawSurface();
                d.setColor(Color.GRAY);
                for (Drawable drawable : background) {
                    drawable.drawOn(d);
                }
                for (Ball ball : balls) {
                    ball.drawOn(d);
                }
                d.setColor(Color.YELLOW);
                for (Drawable drawable : foreground) {
                    drawable.drawOn(d);
                }
                gui.show(d);
            }

            /**
             * Updates the state of the balls.
             */
            @Override
            public void update() {
                for (Ball ball : balls) {
                    for (Line line : lines) {
                        if (ball.isIntersecting(line)) {
                            ball.getCollider().collide(line);
                        }
                    }
                }
                for (Ball ball : balls) {
                    ball.getCollider().apply();
                }
                for (Ball ball : balls) {
                    ball.moveOneStep();
                }
            }
        }.start();
    }

    /**
     * Generates a random center for the ball.
     *
     * @param intArgs the array of integers
     * @param rect1   the first rectangle
     * @param rect2   the second rectangle
     * @param i       the index of the ball
     * @return the new center of the ball
     */
    private static Point generateCenter(int[] intArgs, Rectangle rect1, Rectangle rect2, int i) {
        if (i < intArgs.length / 2) {
            while (true) {
                double x = MathUtils.randomDouble(intArgs[i] + rect2.topLeft().getX(),
                        intArgs[i] + rect2.topLeft().getX() + rect2.getWidth() - intArgs[i]);
                double y = MathUtils.randomDouble(intArgs[i] + rect2.topLeft().getY(),
                        intArgs[i] + rect2.topLeft().getY() + rect2.getHeight() - intArgs[i]);
                Ball ball = new Ball(x, y, intArgs[i], null);
                if (rect2.contains(ball) && rect1.doesntContain(ball)) {
                    return new Point(x, y);
                }
            }
        } else {
            while (true) {
                double x = MathUtils.randomDouble(intArgs[i], Constants.WIDTH - intArgs[i]);
                double y = MathUtils.randomDouble(intArgs[i], Constants.HEIGHT - intArgs[i]);
                Ball ball = new Ball(x, y, intArgs[i], null);
                if (rect2.doesntContain(ball) && rect1.doesntContain(ball)) {
                    return new Point(x, y);
                }
            }
        }
    }
}
