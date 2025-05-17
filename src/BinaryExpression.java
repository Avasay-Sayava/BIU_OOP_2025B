import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a new binary expression.
     *
     * @param l the left sub-expression
     * @param r the right sub-expression
     */
    public BinaryExpression(Expression l, Expression r) {
        this.left = l.copy();
        this.right = r.copy();
    }

    /**
     * @return the left expression
     */
    public Expression getLeft() {
        return this.left.copy();
    }

    /**
     * @return the right expression
     */
    public Expression getRight() {
        return this.right.copy();
    }

    /**
     * @return a list of all the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        List<String> vars = this.left.getVariables();
        vars.addAll(this.right.getVariables());
        return vars;
    }

    /**
     * @return a copy of the expression
     */
    @Override
    public abstract BinaryExpression copy();

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public abstract BinaryExpression assign(String var, Expression expression);

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
                && (Objects.equals(left, ((BinaryExpression) o).left)
                && Objects.equals(right, ((BinaryExpression) o).right)
                || Objects.equals(left, ((BinaryExpression) o).right)
                && Objects.equals(right, ((BinaryExpression) o).left));
    }

    /**
     * Returns the hash code of the expression based on its left and right expressions.
     *
     * @return the hash code of the expression
     */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), this.left, this.right);
    }
}
