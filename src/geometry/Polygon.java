package geometry;

/**
 * This class represents a polygon in 2D space.
 */
public class Polygon implements Shape {
    private final Point[] vertices;
    private final int numVertices;
    private double area;
    private Rectangle bounds;

    /**
     * Constructor.
     *
     * @param vertices the vertices of the polygon
     */
    public Polygon(Point... vertices) {
        this.vertices = new Point[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            this.vertices[i] = new Point(vertices[i]);
        }
        this.numVertices = vertices.length;
        this.bounds = null;
        this.area = -1;
    }

    /**
     * @param index the index of the vertex
     * @return the vertex at the given index
     */
    public Point getVertex(int index) {
        return new Point(this.vertices[index]);
    }

    /**
     * @return the number of the vertices in the polygon
     */
    public int getNumVertices() {
        return this.numVertices;
    }

    /**
     * @return the area of the polygon
     */
    public double calculateArea() {
        double area = 0;
        for (int i = 0; i < numVertices; i++) {
            int j = (i + 1) % numVertices;
            area += vertices[i].getX() * vertices[j].getY();
            area -= vertices[j].getX() * vertices[i].getY();
        }
        return Math.abs(area) / 2.0;
    }

    /**
     * @param ignored ignored parameter for compatibility
     * @return the shape as a polygon, or closest approximation
     */
    @Override
    public Polygon getPolygon(int ignored) {
        return this;
    }

    /**
     * @param other the shape to check for intersection
     * @return true if the polygon intersects with the other shape, false otherwise
     */
    @Override
    public boolean isIntersecting(Shape other) {
        Line[] lines = this.getLines();
        for (Line line : lines) {
            if (other.isIntersecting(line)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param other the polygon to check for intersection
     * @return the line that intersects with the other polygon, or null if no intersection
     */
    @Override
    public Line isIntersecting(Polygon other) {
        Line[] lines = this.getLines();
        Line[] otherLines = other.getLines();
        for (Line line : lines) {
            for (Line otherLine : otherLines) {
                if (line.isIntersecting(otherLine)) {
                    return otherLine;
                }
            }
        }
        return null;
    }

    /**
     * @return the lines of the polygon
     */
    public Line[] getLines() {
        Line[] lines = new Line[this.numVertices];
        for (int i = 0; i < this.numVertices; i++) {
            lines[i] = new Line(this.vertices[i], this.vertices[(i + 1) % this.numVertices]);
        }
        return lines;
    }

    /**
     * @param line the line to check for intersection
     * @return true if the polygon intersects with the line, false otherwise
     */
    @Override
    public boolean isIntersecting(Line line) {
        Line[] lines = this.getLines();
        for (Line l : lines) {
            if (l.isIntersecting(line)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the bounding rectangle of the polygon
     */
    @Override
    public Rectangle calculateBounds() {
        double minX = this.vertices[0].getX();
        double maxX = this.vertices[0].getX();
        double minY = this.vertices[0].getY();
        double maxY = this.vertices[0].getY();

        for (int i = 1; i < this.numVertices; i++) {
            if (this.vertices[i].getX() < minX) {
                minX = this.vertices[i].getX();
            }
            if (this.vertices[i].getX() > maxX) {
                maxX = this.vertices[i].getX();
            }
            if (this.vertices[i].getY() < minY) {
                minY = this.vertices[i].getY();
            }
            if (this.vertices[i].getY() > maxY) {
                maxY = this.vertices[i].getY();
            }
        }

        return new Rectangle(new Point(minX, minY), new Point(maxX, maxY));
    }

    /**
     * @return the bounding rectangle of the polygon
     */
    @Override
    public Rectangle getBounds() {
        if (this.bounds == null) {
            this.bounds = calculateBounds();
        }
        return this.bounds;
    }

    /**
     * @param point the point to check
     * @return true if the polygon contains the point, false otherwise
     */
    @Override
    public boolean contains(Point point) {
        return false;
    }

    /**
     * @param point the point to check
     * @return true if the polygon does not contain the point, false otherwise
     */
    @Override
    public boolean doesntContain(Point point) {
        return false;
    }

    /**
     * @return the area of the polygon
     */
    @Override
    public double getArea() {
        if (this.area < 0) {
            this.area = calculateArea();
        }
        return this.area;
    }
}
