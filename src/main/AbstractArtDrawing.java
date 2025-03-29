package src.main;

import biuoop.GUI;
import biuoop.DrawSurface;
import src.geometry.Line;
import src.geometry.Point;

import java.util.Random;
import java.awt.Color;

/**
 * A class that draws random lines and their intersections and middles.
 */
public class AbstractArtDrawing {
    // Constants
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_LINES = 10;
    private static final int POINT_RADIUS = 3;

    // Random number generator
    private final Random rand = new Random();

    /**
     * @return A random line
     */
    private Line randomLine() {
        int x1 = this.rand.nextInt(WIDTH) + 1;
        int y1 = this.rand.nextInt(HEIGHT) + 1;
        int x2 = this.rand.nextInt(WIDTH) + 1;
        int y2 = this.rand.nextInt(HEIGHT) + 1;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Draws triangles from the intersections of the lines.
     *
     * @param lines The lines to intersect
     * @param d     The DrawSurface to draw on
     */
    private void drawTriangles(Line[] lines, DrawSurface d) {
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                for (int k = j + 1; k < lines.length; k++) {
                    if (Line.isIntersecting(lines[i], lines[j], lines[k])) {
                        drawTriangle(lines[i].intersectionWith(lines[j]),
                                lines[i].intersectionWith(lines[k]),
                                lines[j].intersectionWith(lines[k]),
                                d);
                    }
                }
            }
        }
    }

    /**
     * Draws a triangle from 3 points.
     *
     * @param p1 The first point
     * @param p2 The second point
     * @param p3 The third point
     * @param d  The DrawSurface to draw on
     */
    private void drawTriangle(Point p1, Point p2, Point p3, DrawSurface d) {
        if (p1 == null || p2 == null || p3 == null) {
            return;
        }
        (new Line(p1, p2)).draw(d);
        (new Line(p1, p3)).draw(d);
        (new Line(p2, p3)).draw(d);
    }

    /**
     * Draws the intersections of the lines.
     *
     * @param lines The lines to intersect
     * @param d     The DrawSurface to draw on
     */
    private void drawIntersections(Line[] lines, DrawSurface d) {
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                Point intersection = lines[i].intersectionWith(lines[j]);
                if (intersection != null) {
                    intersection.draw(d, POINT_RADIUS);
                }
            }
        }
    }

    /**
     * Draws the middle points of the lines.
     *
     * @param lines The lines to draw the middle points of
     * @param d     The DrawSurface to draw on
     */
    private void drawMiddlePoints(Line[] lines, DrawSurface d) {
        for (Line line : lines) {
            line.middle().draw(d, POINT_RADIUS);
        }
    }

    /**
     * Draws modern art with random lines.
     */
    public void drawModernArt() {
        GUI gui = new GUI("AbstractArtDrawing", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[NUM_LINES];

        d.setColor(Color.BLACK);
        for (int i = 0; i < NUM_LINES; ++i) {
            lines[i] = randomLine();
            lines[i].draw(d);
        }

        d.setColor(Color.GREEN);
        drawTriangles(lines, d);
        d.setColor(Color.RED);
        drawIntersections(lines, d);
        d.setColor(Color.BLUE);
        drawMiddlePoints(lines, d);

        gui.show(d);
    }

    /**
     * Main method.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing drawing = new AbstractArtDrawing();
        drawing.drawModernArt();
    }
}
