import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing an unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression expression;

    /**
     * Constructs a new unary expression.
     *
     * @param e the sub-expression
     */
    public UnaryExpression(Expression e) {
        this.expression = e.copy();
    }

    /**
     * @return the expression inside the unary expression
     */
    public Expression getExpression() {
        return this.expression.copy();
    }

    /**
     * @return all the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return this.expression.getVariables();
    }

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public abstract UnaryExpression assign(String var, Expression expression);

    /**
     * @return a copy of the expression
     */
    @Override
    public abstract UnaryExpression copy();

    /**
     * @return the expression in Nand form
     */
    public abstract Nand nandify();

    /**
     * @return the expression in Nor form
     */
    public abstract Nor norify();

    /**
     * @param o the object to compare to
     * @return true if the two expressions are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass()
                && Objects.equals(expression, ((UnaryExpression) o).expression);
    }

    /**
     * Returns the hash code of the expression based on its sub-expression.
     *
     * @return the hash code of the expression
     */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), this.expression);
    }
}
