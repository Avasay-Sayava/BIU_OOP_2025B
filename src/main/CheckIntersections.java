package src.main;

import biuoop.DrawSurface;
import biuoop.GUI;
import src.geometry.Line;
import src.geometry.Point;

import java.awt.Color;

/**
 * A class that checks for intersections between lines and draws them.
 */
public class CheckIntersections {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final int NUM_LINES = 36;
    private static final int POINT_RADIUS = 3;

    /**
     * Draws the intersections of the lines on the given DrawSurface.
     *
     * @param lines The lines to check for intersections
     * @param d     The DrawSurface to draw on
     */
    private static void drawIntersections(Line[] lines, DrawSurface d) {
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
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("CheckIntersections", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[NUM_LINES];

        lines[0] = new Line(100, 100, 200, 100);
        lines[1] = new Line(100, 100, 100, 200);
        lines[2] = new Line(200, 100, 200, 200);
        lines[3] = new Line(100, 200, 200, 200);

        lines[4] = new Line(350, 100, 400, 150);
        lines[5] = new Line(400, 150, 350, 200);
        lines[6] = new Line(350, 200, 300, 150);
        lines[7] = new Line(300, 150, 350, 100);

        lines[8] = new Line(500, 100, 600, 100);
        lines[9] = new Line(500, 100, 600, 200);
        lines[10] = new Line(600, 100, 500, 200);
        lines[11] = new Line(500, 200, 600, 200);

        lines[12] = new Line(100, 300, 150, 300);
        lines[13] = new Line(150, 300, 200, 300);
        lines[14] = new Line(100, 325, 150, 325);
        lines[15] = new Line(200, 325, 150, 325);
        lines[16] = new Line(150, 350, 100, 350);
        lines[17] = new Line(200, 350, 150, 350);
        lines[18] = new Line(150, 375, 100, 375);
        lines[19] = new Line(150, 375, 200, 375);
        lines[20] = new Line(175, 400, 100, 400);
        lines[21] = new Line(125, 400, 200, 400);

        lines[22] = new Line(300, 300, 325, 300);
        lines[23] = new Line(375, 300, 400, 300);
        lines[24] = new Line(300, 400, 400, 400);
        lines[25] = new Line(350, 400, 350, 350);

        lines[26] = new Line(500, 300, 500, 350);
        lines[27] = new Line(500, 350, 500, 400);
        lines[28] = new Line(525, 350, 525, 300);
        lines[29] = new Line(525, 350, 525, 400);
        lines[30] = new Line(550, 300, 550, 350);
        lines[31] = new Line(550, 400, 550, 350);
        lines[32] = new Line(575, 350, 575, 300);
        lines[33] = new Line(575, 400, 575, 350);
        lines[34] = new Line(600, 300, 600, 375);
        lines[35] = new Line(600, 325, 600, 400);

        for (Line line : lines) {
            d.setColor(Color.BLACK);
            line.draw(d);
        }

        d.setColor(Color.RED);
        drawIntersections(lines, d);

        gui.show(d);
    }
}
