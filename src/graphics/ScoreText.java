package graphics;

import biuoop.DrawSurface;
import geometry.Point;
import util.Counter;

/**
 * A ScoreText is a text that displays the score.
 */
public class ScoreText extends Text {
    private final Counter score;

    /**
     * Copy constructor.
     *
     * @param other The point to copy
     * @param score The score to display
     */
    public ScoreText(Point other, Counter score) {
        this(other.getX(), other.getY(), score);
    }

    /**
     * Constructor.
     *
     * @param x     The x coordinate
     * @param y     The y coordinate
     * @param score The score to display
     */
    public ScoreText(double x, double y, Counter score) {
        super(x, y, "");
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText((int) Math.round(getX()), (int) Math.round(getY()),
                String.format("Score: %03d", this.score.getValue()), 20);
    }
}
