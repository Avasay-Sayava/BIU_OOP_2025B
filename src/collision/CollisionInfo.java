package collision;

import geometry.Point;
import util.Pair;

/**
 * This class represents the collision information between two objects.
 */
public class CollisionInfo extends Pair<Point, Collidable> {
    /**
     * Constructor.
     *
     * @param first  the first object
     * @param second the second object
     */
    public CollisionInfo(Point first, Collidable second) {
        super(first, second);
    }

    /**
     * @return the point of collision
     */
    public Point collisionPoint() {
        return this.getFirst();
    }

    /**
     * @return the object that was collided with
     */
    public Collidable collisionObject() {
        return this.getSecond();
    }
}
