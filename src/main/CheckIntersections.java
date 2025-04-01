package main;

import biuoop.DrawSurface;
import biuoop.GUI;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * A class that checks for intersections between lines and draws them.
 */
public class CheckIntersections {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;
    private static final int NUM_LINES = 108;
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

        lines[36] = new Line(100, 500, 200, 500);
        lines[37] = new Line(200, 500, 150, 500);
        lines[38] = new Line(100, 525, 200, 525);
        lines[39] = new Line(150, 525, 200, 525);
        lines[40] = new Line(200, 550, 100, 550);
        lines[41] = new Line(200, 550, 150, 550);
        lines[42] = new Line(200, 575, 100, 575);
        lines[43] = new Line(150, 575, 100, 575);
        lines[44] = new Line(100, 600, 125, 600);
        lines[45] = new Line(125, 600, 100, 600);
        lines[46] = new Line(175, 600, 200, 600);
        lines[47] = new Line(175, 600, 200, 600);

        lines[48] = new Line(300, 500, 300, 600);
        lines[49] = new Line(300, 600, 400, 500);
        lines[50] = new Line(400, 600, 400, 500);
        lines[51] = new Line(400, 600, 300, 500);

        lines[52] = new Line(500, 500, 500, 525);
        lines[53] = new Line(500, 575, 500, 600);
        lines[54] = new Line(600, 500, 600, 600);
        lines[55] = new Line(550, 550, 600, 550);

        lines[56] = new Line(700, 100, 700, 200);
        lines[57] = new Line(700, 200, 700, 150);
        lines[58] = new Line(725, 100, 725, 200);
        lines[59] = new Line(725, 150, 725, 200);
        lines[60] = new Line(750, 200, 750, 100);
        lines[61] = new Line(750, 200, 750, 150);
        lines[62] = new Line(775, 200, 775, 100);
        lines[63] = new Line(775, 150, 775, 100);
        lines[64] = new Line(800, 100, 800, 125);
        lines[65] = new Line(800, 125, 800, 100);
        lines[66] = new Line(800, 175, 800, 200);
        lines[67] = new Line(800, 175, 800, 200);

        for (int i = 0; i < 20; i++) {
            double theta = Math.toRadians(360 / 20.0);
            lines[i + 68] = new Line(750 + 50 * Math.cos(i * theta), 350 + 50 * Math.sin(i * theta),
                    750 + 50 * Math.cos((i + 1) * theta), 350 + 50 * Math.sin((i + 1) * theta));
        }

        for (int i = 0; i < 20; i++) {
            double distance = 30 + 20 * Math.abs(Math.sin(Math.toRadians(i * 360 / 20.0)));
            double nextDistance = 30 + 20 * Math.abs(Math.sin(Math.toRadians((i + 1) * 360 / 20.0)));
            double theta = Math.toRadians(360 / 20.0);
            lines[i + 88] = new Line(750 + distance * Math.cos(i * theta), 550 + distance * Math.sin(i * theta),
                    750 + nextDistance * Math.cos((i + 1) * theta), 550 + nextDistance * Math.sin((i + 1) * theta));
        }

        d.setColor(Color.BLACK);
        for (Line line : lines) {
            line.draw(d);
        }

        d.setColor(Color.RED);
        drawIntersections(lines, d);

        gui.show(d);
    }
}
