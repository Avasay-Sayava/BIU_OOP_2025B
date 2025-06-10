package graphics.animation;

import java.awt.Image;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * An animator is a class that can be used to animate a sprite.
 */
public abstract class Animator implements Supplier<Image> {
    public static final Animator EMPTY = new Animator() {
        @Override
        public Collection<Image> getFrames() {
            return List.of();
        }

        @Override
        public Image get() {
            return null;
        }
    };

    private final Timestamp timestamp;

    /**
     * Constructor.
     *
     * @param timestamp the timestamp to copy
     */
    public Animator(Timestamp timestamp) {
        this.timestamp = new Timestamp(timestamp);
    }

    /**
     * Constructor.
     */
    public Animator() {
        this(new Timestamp());
    }

    /**
     * @return the frames of the animation
     */
    public abstract Collection<Image> getFrames();

    /**
     * @return the current timestamp of the animation
     */
    public Timestamp getTimestamp() {
        return new Timestamp(this.timestamp);
    }

    /**
     * Resets the timestamp of the animation.
     */
    public void reset() {
        this.timestamp.reset();
    }
}
