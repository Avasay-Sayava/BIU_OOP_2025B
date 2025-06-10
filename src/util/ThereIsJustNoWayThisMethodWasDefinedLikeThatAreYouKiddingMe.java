package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark methods that are defined in a way that is.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface ThereIsJustNoWayThisMethodWasDefinedLikeThatAreYouKiddingMe {
    /**
     * A message to be displayed when the annotation is used.
     *
     * @return the message
     */
    String value() default "There is a window nearby, and I think I'm going to defenestrate.";
}
