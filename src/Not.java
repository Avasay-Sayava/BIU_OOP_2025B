import java.util.Map;

/**
 * This class represents a logical NOT expression.
 */
public class Not extends UnaryExpression {
    /**
     * Constructs a Not expression, representing the logical NOT operation
     * on a sub-expression.
     *
     * @param e the sub-expression
     */
    public Not(Expression e) {
        super(e);
    }

    /**
     * @param assignment assignment of variables
     * @return the value of the expression
     * @throws VariableNotFoundException if a variable in the expression is not found in the assignment
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws VariableNotFoundException {
        return !getExpression().evaluate(assignment);
    }

    /**
     * @return the value of the expression
     * @throws VariableNotFoundException if there is a variable in the expression
     */
    @Override
    public Boolean evaluate() throws VariableNotFoundException {
        return !getExpression().evaluate();
    }

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public Not assign(String var, Expression expression) {
        return new Not(getExpression().assign(var, expression));
    }

    /**
     * @return a copy of the expression
     */
    @Override
    public Not copy() {
        return new Not(getExpression());
    }

    /**
     * @return the expression in Nand form
     */
    @Override
    public Nand nandify() {
        Expression nand = getExpression().nandify();
        return new Nand(nand, nand);
    }

    /**
     * @return the expression in Nor form
     */
    @Override
    public Nor norify() {
        Expression nor = getExpression().norify();
        return new Nor(nor, nor);
    }

    /**
     * @return the expression in simplified form
     */
    @Override
    public Expression simplify() {
        Not simplified = new Not(getExpression().simplify());
        if (simplified.getVariables().isEmpty()) {
            try {
                return new Val(simplified.evaluate());
            } catch (VariableNotFoundException e) {
                throw new RuntimeException("Not.simplify evaluate() failed", e);
            }
        }
        return simplified;
    }

    /**
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return "~(" + getExpression().toString() + ")";
    }
}
