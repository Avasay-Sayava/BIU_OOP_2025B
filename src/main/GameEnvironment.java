package main;

import collision.Collidable;
import collision.Colliding;
import collision.CollisionInfo;
import geometry.Line;
import util.DisgustingButYouSaidINeedToHaveItYey;
import util.ValueSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

/**
 * A class that represents a game environment.
 */
public class GameEnvironment {
    private final Set<Collidable> collidables;
    private final Set<Colliding> colliding;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ValueSet<>();
        this.colliding = new ValueSet<>();
    }

    /**
     * Adds a collidable to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Adds a colliding to the environment.
     *
     * @param c the colliding to add
     */
    public void addColliding(Colliding c) {
        this.colliding.add(c);
    }

    /**
     * Clears the collidables from the environment.
     */
    public void clearCollidables() {
        this.collidables.clear();
    }

    /**
     * Clears the colliding from the environment.
     */
    public void clearColliding() {
        this.colliding.clear();
    }

    /**
     * Removes a collidable from the environment.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Removes a colliding from the environment.
     *
     * @param c the colliding to remove
     */
    public void removeColliding(Colliding c) {
        this.colliding.remove(c);
    }

    /**
     * Clears the environment of all collidables and colliding objects.
     */
    public void clear() {
        this.clearCollidables();
        this.clearColliding();
    }

    /**
     * Updates the environment each frame by checking for collisions.
     */
    public void update() {
        ArrayList<Colliding> colliding = new ArrayList<>(this.colliding);
        ArrayList<Collidable> collidables = new ArrayList<>(this.collidables);
        for (Colliding c : colliding) {
            for (Collidable collidable : collidables) {
                if (c.isIntersecting(collidable)) {
                    c.getCollider().collide(collidable);
                }
            }
            c.getCollider().apply();
        }
    }

    /**
     * Returns the closest collision point of a trajectory with the collidables in the environment.
     *
     * @param trajectory the trajectory to check for collisions
     * @return the closest collision point, or null if there are no collisions
     */
    @DisgustingButYouSaidINeedToHaveItYey
    @SuppressWarnings("unused")
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable collidable = this.collidables.stream()
                .filter(c1 -> c1.isIntersecting(trajectory))
                .min(Comparator.comparingDouble((Collidable c) -> c.getCenter()
                        .distance(trajectory.getStart()))).orElse(null);
        if (collidable == null) {
            return null;
        }
        return new CollisionInfo(collidable.getCenter(), collidable);
    }
}
