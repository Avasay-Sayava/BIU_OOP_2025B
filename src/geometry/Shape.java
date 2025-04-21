package geometry;

/**
 * This interface represents a shape in 2D space.
 */
public interface Shape {
    /**
     * @return the center of the shape
     */
    double getArea();

    /**
     * @param accuracy the accuracy of the polygon
     * @return a polygon that represents the shape
     */
    Polygon getPolygon(int accuracy);

    /**
     * @param other the shape to check for intersection
     * @return true if the shape intersects with the other shape, false otherwise
     */
    boolean isIntersecting(Shape other);

    /**
     * @param other the polygon to check for intersection
     * @return the line that intersects with the other polygon, or null if no intersection
     */
    default Line isIntersecting(Polygon other) {
        Line[] lines = other.getLines();
        for (Line line : lines) {
            if (this.isIntersecting(line)) {
                return line;
            }
        }
        return null;
    }

    /**
     * @param line the line to check for intersection
     * @return true if the shape intersects with the line, false otherwise
     */
    boolean isIntersecting(Line line);

    /**
     * @return the lines of the shape
     */
    Rectangle calculateBounds();

    /**
     * @return the area of the shape
     */
    double calculateArea();

    /**
     * @return the perimeter of the shape
     */
    Rectangle getBounds();

    /**
     * @param point the point to check
     * @return true if the shape contains the point, false otherwise
     */
    boolean contains(Point point);

    /**
     * @param point the point to check
     * @return true if the shape does not contain the point, false otherwise
     */
    boolean doesntContain(Point point);
}
