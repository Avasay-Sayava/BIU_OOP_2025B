package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to mark methods or fields that are considered disgusting.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface DisgustingButYouSaidINeedToHaveItYey {
    /**
     * @return The reason why the method is disgusting
     */
    String value() default "This method is absolutely disgusting but you said I need to have it. Me being mad.";
}
