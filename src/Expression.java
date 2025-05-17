import java.util.List;
import java.util.Map;

/**
 * Interface representing a boolean expression.
 */
public interface Expression {
    /**
     * @param assignment assignment of variables
     * @return the value of the expression
     * @throws VariableNotFoundException if a variable in the expression is not found in the assignment
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws VariableNotFoundException;

    /**
     * @return the value of the expression
     * @throws VariableNotFoundException if there is a variable in the expression
     */
    Boolean evaluate() throws VariableNotFoundException;

    /**
     * @return a list of all the variables in the expression
     */
    List<String> getVariables();

    /**
     * @return a string representation of the expression
     */
    @Override
    String toString();

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    Expression assign(String var, Expression expression);

    /**
     * @return a copy of the expression
     */
    Expression copy();

    /**
     * @return the expression in Nand form
     */
    Expression nandify();

    /**
     * @return the expression in Nor form
     */
    Expression norify();

    /**
     * @return the expression in simplified form
     */
    Expression simplify();
}
