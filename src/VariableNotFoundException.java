import java.io.Serial;

/**
 * Exception thrown when a variable is not found in an assignment.
 */
public class VariableNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new VariableNotFoundException with the specified variable name.
     *
     * @param var the name of the variable that was not found
     * @return the detail message for the exception
     */
    private static String message(String var) {
        return "Variable " + var + " not found in the assignment.";
    }

    /**
     * Constructs a new VariableNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public VariableNotFoundException(String message) {
        super(message(message));
    }

    /**
     * Constructs a new VariableNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public VariableNotFoundException(String message, Throwable cause) {
        super(message(message), cause);
    }

    /**
     * Constructs a new VariableNotFoundException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public VariableNotFoundException(Throwable cause) {
        super(cause);
    }
}
