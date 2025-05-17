import java.util.Map;

/**
 * This class represents a logical XNOR expression.
 */
public class Xnor extends BinaryExpression {
    /**
     * Constructs a Xnor expression, representing the logical XNOR operation
     * between two sub-expressions.
     *
     * @param l the left sub-expression
     * @param r the right sub-expression
     */
    public Xnor(Expression l, Expression r) {
        super(l, r);
    }

    /**
     * @param assignment assignment of variables
     * @return the value of the expression
     * @throws VariableNotFoundException if a variable in the expression is not found in the assignment
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws VariableNotFoundException {
        return getLeft().evaluate(assignment) == getRight().evaluate(assignment);
    }

    /**
     * @return the value of the expression
     * @throws VariableNotFoundException if there is a variable in the expression
     */
    @Override
    public Boolean evaluate() throws VariableNotFoundException {
        return getLeft().evaluate() == getRight().evaluate();
    }

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public Xnor assign(String var, Expression expression) {
        return new Xnor(getLeft().assign(var, expression), getRight().assign(var, expression));
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
        Nand notNand = new Nand(notA, notB);
        Nand nand = new Nand(left, right);
        return new Nand(notNand, nand);
    }

    /**
     * @return the expression in Nor form
     */
    @Override
    public Nor norify() {
        Expression left = getLeft().norify();
        Expression right = getRight().norify();
        Nor nor = new Nor(left, right);
        Nor norA = new Nor(left, nor);
        Nor norB = new Nor(nor, right);
        return new Nor(norA, norB);
    }

    /**
     * @return the expression in simplified form
     */
    @Override
    public Expression simplify() {
        Xnor simplified = new Xnor(getLeft().simplify(), getRight().simplify());
        if (simplified.getVariables().isEmpty()) {
            try {
                return new Val(simplified.evaluate());
            } catch (VariableNotFoundException e) {
                throw new RuntimeException("Xnor.simplify evaluate() failed", e);
            }
        }
        if (simplified.getLeft().equals(simplified.getRight())) {
            return new Val(true);
        }
        if (simplified.getLeft().equals(new Val(true))) {
            return simplified.getRight();
        }
        if (simplified.getRight().equals(new Val(true))) {
            return simplified.getLeft();
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
    public Xnor copy() {
        return new Xnor(getLeft(), getRight());
    }

    /**
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return "(" + getLeft().toString() + " # " + getRight().toString() + ")";
    }
}
