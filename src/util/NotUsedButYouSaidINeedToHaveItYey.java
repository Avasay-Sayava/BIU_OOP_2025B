package util;

public @interface Redundant {
    /**
     * This annotation is used to mark methods that are redundant and can be removed.
     * It is used for documentation purposes only.
     */
    String value() default "This method is redundant and can be removed.";
}
