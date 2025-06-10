package graphics.animation;

import graphics.Sprite;

/**
 * An interface that represents an animated sprite.
 * It extends the Sprite interface and adds an animator.
 */
public interface AnimatedSprite extends Sprite {
    /**
     * @return the animator of this sprite
     */
    Animator getAnimator();
}
