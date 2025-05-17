import java.util.Map;

/**
 * This class represents a logical NOR expression.
 */
public class Nor extends BinaryExpression {
    /**
     * Constructs a Nor expression, representing the logical NOR operation
     * between two sub-expressions.
     *
     * @param l the left sub-expression
     * @param r the right sub-expression
     */
    public Nor(Expression l, Expression r) {
        super(l, r);
    }

    /**
     * @param assignment assignment of variables
     * @return the value of the expression
     * @throws VariableNotFoundException if a variable in the expression is not found in the assignment
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws VariableNotFoundException {
        return !(getLeft().evaluate(assignment) || getRight().evaluate(assignment));
    }

    /**
     * @return the value of the expression
     * @throws VariableNotFoundException if there is a variable in the expression
     */
    @Override
    public Boolean evaluate() throws VariableNotFoundException {
        return !(getLeft().evaluate() || getRight().evaluate());
    }

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public Nor assign(String var, Expression expression) {
        return new Nor(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * @return the expression in Nand form
     */
    @Override
    public Nand nandify() {
        Expression left = getLeft().nandify();
        Expression right = getRight().nandify();
        Nand notA = new Nand(left, left);
        Nand notB = new Nand(right, right);
        Nand nand = new Nand(notA, notB);
        return new Nand(nand, nand);
    }

    /**
     * @return the expression in Nor form
     */
    @Override
    public Nor norify() {
        return new Nor(getLeft().norify(), getRight().norify());
    }

    /**
     * @return the expression in simplified form
     */
    @Override
    public Expression simplify() {
        Nor simplified = new Nor(getLeft().simplify(), getRight().simplify());
        if (simplified.getVariables().isEmpty()) {
            try {
                return new Val(simplified.evaluate());
            } catch (VariableNotFoundException e) {
                throw new RuntimeException("Nor.simplify evaluate() failed", e);
            }
        }
        if (simplified.getLeft().equals(simplified.getRight())) {
            return new Not(simplified.getLeft());
        }
        if (simplified.getLeft().equals(new Val(true))
                || simplified.getRight().equals(new Val(true))) {
            return new Val(false);
        }
        if (simplified.getLeft().equals(new Val(false))) {
            return new Not(simplified.getRight());
        }
        if (simplified.getRight().equals(new Val(false))) {
            return new Not(simplified.getLeft());
        }
        return simplified;
    }

    /**
     * @return a copy of the expression
     */
    @Override
    public Nor copy() {
        return new Nor(getLeft(), getRight());
    }

    /**
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return "(" + getLeft().toString() + " V " + getRight().toString() + ")";
    }
}
