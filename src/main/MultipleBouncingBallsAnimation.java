package main;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geometry.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import graphics.Drawable;
import util.Constants;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class BouncingBallAnimation {
    public static void main(String[] args) {
        Point point = new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        new Thread(() -> drawAnimation(point, dx, dy)).start();
    }

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", Constants.WIDTH, Constants.HEIGHT);
        Sleeper sleeper = new Sleeper();
        Rectangle rect1 = new Rectangle(450, 450, 600, 600);
        Rectangle rect2 = new Rectangle(50, 50, 500, 500);
        Drawable[] foreground = {rect1};
        Drawable[] background = {rect2};
        Ball[] balls = {new Ball(start.getX(), start.getY(), Constants.BALL_RADIUS, Color.BLACK),
                new Ball(start.getX() + 25, start.getY() + 25, Constants.BALL_RADIUS, Color.RED),
                new Ball(start.getX() + 50, start.getY() + 50, Constants.BALL_RADIUS, Color.GREEN),
                new Ball(start.getX() + 75, start.getY() + 75, Constants.BALL_RADIUS, Color.BLUE)};
        for (int i = 0; i < balls.length; i++) {
            balls[i].setVelocity(dx + i, dy + i);
        }
        ArrayList<Line> lines = new ArrayList<>(List.of(
                new Line(0, 0, Constants.WIDTH, 0),
                new Line(Constants.WIDTH, 0, Constants.WIDTH, Constants.HEIGHT),
                new Line(Constants.WIDTH, Constants.HEIGHT, 0, Constants.HEIGHT),
                new Line(0, Constants.HEIGHT, 0, 0)
        ));
        lines.addAll(List.of(rect1.getLines()));
        lines.addAll(List.of(rect2.getLines()));
        while (true) {
            long time = System.currentTimeMillis();
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
            int elapsedTime = (int) (System.currentTimeMillis() - time);
            int sleepTime = (int) Math.round(1E3 / Constants.FPS) - elapsedTime;
            sleeper.sleepFor(Math.max(sleepTime, 0));
        }
    }
}
