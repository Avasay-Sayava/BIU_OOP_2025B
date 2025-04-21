package graphics;

import biuoop.DrawSurface;

/**
 * This interface represents an object that can be drawn on a DrawSurface.
 */
public interface Drawable {
    /**
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);
}
