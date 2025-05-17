import java.util.Map;

/**
 * This class represents a logical XOR expression.
 */
public class Xor extends BinaryExpression {
    /**
     * Constructs a Xor expression, representing the logical XOR operation
     * between two sub-expressions.
     *
     * @param l the left sub-expression
     * @param r the right sub-expression
     */
    public Xor(Expression l, Expression r) {
        super(l, r);
    }

    /**
     * @param assignment assignment of variables
     * @return the value of the expression
     * @throws VariableNotFoundException if a variable in the expression is not found in the assignment
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws VariableNotFoundException {
        return getLeft().evaluate(assignment) ^ getRight().evaluate(assignment);
    }

    /**
     * @return the value of the expression
     * @throws VariableNotFoundException if there is a variable in the expression
     */
    @Override
    public Boolean evaluate() throws VariableNotFoundException {
        return getLeft().evaluate() ^ getRight().evaluate();
    }

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public Xor assign(String var, Expression expression) {
        return new Xor(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    /**
     * @return the expression in Nand form
     */
    @Override
    public Nand nandify() {
        Expression left = getLeft().nandify();
        Expression right = getRight().nandify();
        Nand nand = new Nand(left, right);
        Nand nandA = new Nand(left, nand);
        Nand nandB = new Nand(nand, right);
        return new Nand(nandA, nandB);
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
        Nor notNor = new Nor(notA, notB);
        Nor nor = new Nor(left, right);
        return new Nor(notNor, nor);
    }

    /**
     * @return the expression in simplified form
     */
    @Override
    public Expression simplify() {
        Xor simplified = new Xor(getLeft().simplify(), getRight().simplify());
        if (simplified.getVariables().isEmpty()) {
            try {
                return new Val(simplified.evaluate());
            } catch (VariableNotFoundException e) {
                throw new RuntimeException("Xor.simplify evaluate() failed", e);
            }
        }
        if (simplified.getLeft().equals(simplified.getRight())) {
            return new Val(false);
        }
        if (simplified.getLeft().equals(new Val(false))) {
            return simplified.getRight();
        }
        if (simplified.getRight().equals(new Val(false))) {
            return simplified.getLeft();
        }
        if (simplified.getLeft().equals(new Val(true))) {
            return new Not(simplified.getRight());
        }
        if (simplified.getRight().equals(new Val(true))) {
            return new Not(simplified.getLeft());
        }
        return simplified;
    }

    /**
     * @return a copy of the expression
     */
    @Override
    public Xor copy() {
        return new Xor(getLeft(), getRight());
    }

    /**
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return "(" + getLeft().toString() + " ^ " + getRight().toString() + ")";
    }
}
