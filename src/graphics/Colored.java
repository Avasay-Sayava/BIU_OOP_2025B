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

    /**
     * Checks if the color of this object is equal to the color of another Colored object.
     *
     * @param other The other Colored object to compare with
     * @return true if the colors are equal, false otherwise
     */
    boolean colorsEqual(Colored other);
}
