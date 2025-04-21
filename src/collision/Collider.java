package collision;

import geometry.Shape;
import physics.Physical;

public abstract class CollisionHandler<T> {
    private final Shape shape;

    public CollisionHandler(Shape shape) {
        this.shape = shape;
    }

    public abstract void collide(Shape object);

    public abstract void collide(Line object);
}
