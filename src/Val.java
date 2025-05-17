import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class representing a boolean value expression.
 */
public class Val implements Expression {
    private final boolean value;

    /**
     * Constructs a new Val expression.
     *
     * @param value the boolean value of the expression
     */
    public Val(boolean value) {
        this.value = value;
    }

    /**
     * @param assignment assignment of variables
     * @return the value of the expression
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) {
        return this.value;
    }

    /**
     * @return the value of the expression
     */
    @Override
    public Boolean evaluate() {
        return this.value;
    }

    /**
     * @return a list of all the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    /**
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return this.value ? "T" : "F";
    }

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public Val assign(String var, Expression expression) {
        return copy();
    }

    /**
     * @return a copy of the expression
     */
    @Override
    public Val copy() {
        return new Val(this.value);
    }

    /**
     * @return the expression in Nand form
     */
    @Override
    public Expression nandify() {
        return copy();
    }

    /**
     * @return the expression in Nor form
     */
    @Override
    public Expression norify() {
        return copy();
    }

    /**
     * @return the expression in simplified form
     */
    @Override
    public Expression simplify() {
        return copy();
    }

    /**
     * @param o the object to compare to
     * @return true if the two expressions are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() && this.value == ((Val) o).value;
    }

    /**
     * Returns the hash code of the expression based on its value.
     *
     * @return the hash code of the expression
     */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), this.value);
    }
}
