package graphics;

import java.awt.Color;

/**
 * This interface represents an object that has a color.
 */
public interface Colored {
    /**
     * @return The color of the object
     */
    Color getColor();

    /**
     * @param color The color to set
     */
    void setColor(Color color);
}
