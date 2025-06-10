package graphics;

import biuoop.DrawSurface;
import util.ValueSet;

import java.util.Comparator;
import java.util.Set;

/**
 * A class that represents a collection of sprites.
 */
public class SpriteCollection {
    private final Set<Sprite> sprites;
    private final Comparator<Sprite> spriteComparator;

    /**
     * Constructor.
     *
     * @param spriteComparator the comparator to sort the sprites
     */
    public SpriteCollection(Comparator<Sprite> spriteComparator) {
        this.spriteComparator = spriteComparator;
        this.sprites = new ValueSet<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Clears the collection of sprites.
     */
    public void clear() {
        this.sprites.clear();
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notifies all sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : this.sprites) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites on the given draw surface.
     *
     * @param d the draw surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites.stream().sorted(this.spriteComparator).toList()) {
            s.drawOn(d);
        }
    }
}
