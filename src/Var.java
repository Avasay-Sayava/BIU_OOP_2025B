import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class representing a variable expression.
 */
public class Var implements Expression {
    private final String name;

    /**
     * Constructs a new Var expression.
     *
     * @param name the name of the variable
     */
    public Var(String name) {
        this.name = name;
    }

    /**
     * @param assignment assignment of variables
     * @return the value of the expression
     * @throws VariableNotFoundException if a variable in the expression is not found in the assignment
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws VariableNotFoundException {
        Boolean value = assignment.getOrDefault(this.name, null);
        if (value == null) {
            throw new VariableNotFoundException(this.name);
        }
        return value;
    }

    /**
     * @return the value of the expression
     * @throws VariableNotFoundException if there is a variable in the expression
     */
    @Override
    public Boolean evaluate() throws VariableNotFoundException {
        throw new VariableNotFoundException(this.name);
    }

    /**
     * @return a list of all the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        return new ArrayList<>(List.of(this.name));
    }

    /**
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Assigns a new expression to a variable in the expression.
     *
     * @param var        the variable to assign
     * @param expression the expression to assign to the variable
     * @return the new expression with the variable assigned
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression.copy();
        }
        return copy();
    }

    /**
     * @return a copy of the expression
     */
    @Override
    public Var copy() {
        return new Var(this.name);
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
        return o != null && getClass() == o.getClass() && Objects.equals(name, ((Var) o).name);
    }

    /**
     * Returns the hash code of the expression based on its value.
     *
     * @return the hash code of the expression
     */
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), this.name);
    }
}
