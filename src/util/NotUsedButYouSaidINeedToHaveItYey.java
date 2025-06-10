package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark methods or fields that are considered not used.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface NotUsedButYouSaidINeedToHaveItYey {
    /**
     * @return The reason why the method is there
     */
    String value() default "This method is not used but you said I need to have it. Yepee!";
}
