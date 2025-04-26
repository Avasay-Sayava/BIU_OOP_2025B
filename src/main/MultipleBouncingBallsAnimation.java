// ID: 326552304

package main;

import biuoop.DrawSurface;
import biuoop.GUI;
import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
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
 * A class for the animation of multiple bouncing balls.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] intArgs = new int[args.length];
        ArrayUtils.toInteger(args, intArgs);
        for (int i = 0; i < intArgs.length; i++) {
            intArgs[i] = Math.max(Math.min(intArgs[i], 100), 5);
        }

        GUI gui = new GUI("MultipleBouncingBallsAnimation", Constants.WIDTH, Constants.HEIGHT);
        ArrayList<Line> lines = new ArrayList<>(List.of(
                new Rectangle(0, 0, Constants.WIDTH, Constants.HEIGHT).getLines()));

        Ball[] balls = new Ball[intArgs.length];
        for (int i = 0; i < intArgs.length; i++) {
            double x = MathUtils.randomDouble(intArgs[i], Constants.WIDTH - intArgs[i]);
            double y = MathUtils.randomDouble(intArgs[i], Constants.HEIGHT - intArgs[i]);
            Color color = Color.getHSBColor(MathUtils.randomFloat(1), 1, 1);
            balls[i] = new Ball(new Point(x, y), intArgs[i], color);
            double speed = BallUtils.getSpeed(intArgs[i]);
            double angle = MathUtils.randomDouble(2 * Math.PI);
            balls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }
        Arrays.sort(balls, Comparator.comparingDouble(Ball::getRadius).reversed());

        new AnimationThread(gui, Constants.FPS) {
            /**
             * Draws the frame.
             * @param gui the GUI to draw on
             */
            @Override
            public void draw(GUI gui) {
                DrawSurface d = gui.getDrawSurface();

                for (Ball ball : balls) {
                    ball.drawOn(d);
                }

                gui.show(d);
            }

            /**
             * Updates the state of the animation.
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
}
