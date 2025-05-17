/*
Name: Aviv Sayer
ID: 326552304
 */
import java.util.TreeMap;

/**
 * Test class for the Expression interface and its implementations.
 */
public class ExpressionsTest {
    /**
     * Main method to test the Expression implementations.
     *
     * @param args command line arguments
     * @throws VariableNotFoundException if a variable in the expression is not found in the assignment
     */
    public static void main(String[] args) throws VariableNotFoundException {
        Expression e = new Xor(new And(new Var("x"), new Var("y")), new Val(true));
        System.out.println(e);
        TreeMap<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        System.out.println(e.evaluate(assignment));
        System.out.println(e.nandify());
        System.out.println(e.norify());
        System.out.println(e.simplify());
    }
}
