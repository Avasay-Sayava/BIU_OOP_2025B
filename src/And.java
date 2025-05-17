import java.util.Map;

/**
 * This class represents a logical AND expression.
 */
public class And extends BinaryExpression {
    /**
     * Constructs a new And expression, which represents the logical "AND" operation
     * on two sub-expressions.
     *
     * @param l the left sub-expression
     * @param r the right sub-expression
     */
    public And(Expression l, Expression r) {
        super(l, r);
    }

    /**
     * @param assignment assignment of variables
     * @return the value of the expression
     * @throws VariableNotFoundException if a variable in the expression is not found in the assignment
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws VariableNotFoundException {
        return getLeft().evaluate(assignment) && getRight().evaluate(assignment);
    }

    /**
     * @return the value of the expression
     * @throws VariableNotFoundException if there is a variable in the expression
     */
    @Override
    public Boolean evaluate() throws VariableNotFoundException {
        return getLeft().evaluate() && getRight().evaluate();
    }

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public And assign(String var, Expression expression) {
        return new And(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * @return the expression in Nand form
     */
    @Override
    public Nand nandify() {
        Nand not = new Nand(getLeft().nandify(), getRight().nandify());
        return new Nand(not, not);
    }

    /**
     * @return the expression in Nor form
     */
    @Override
    public Nor norify() {
        Expression left = getLeft().norify();
        Expression right = getRight().norify();
        Nor notA = new Nor(left, left);
        Nor notB = new Nor(right, right);
        return new Nor(notA, notB);
    }

    /**
     * @return the expression in simplified form
     */
    @Override
    public Expression simplify() {
        And simplified = new And(getLeft().simplify(), getRight().simplify());
        if (simplified.getVariables().isEmpty()) {
            try {
                return new Val(simplified.evaluate());
            } catch (VariableNotFoundException e) {
                throw new RuntimeException("And.simplify evaluate() failed", e);
            }
        }
        if (simplified.getLeft().equals(simplified.getRight())) {
            return simplified.getLeft();
        }
        if (simplified.getLeft().equals(new Val(false))
                || simplified.getRight().equals(new Val(false))) {
            return new Val(false);
        }
        if (simplified.getLeft().equals(new Val(true))) {
            return simplified.getRight();
        }
        if (simplified.getRight().equals(new Val(true))) {
            return simplified.getLeft();
        }
        return simplified;
    }

    /**
     * @return a copy of the expression
     */
    @Override
    public And copy() {
        return new And(getLeft(), getRight());
    }

    /**
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return "(" + getLeft().toString() + " & " + getRight().toString() + ")";
    }
}
