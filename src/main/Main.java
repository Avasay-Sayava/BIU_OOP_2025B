/*
Name: Aviv Sayer
ID: 326552304
 */

package main;

/**
 * The main class.
 */
public class Main {
    private static Ass5Game game;
    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.game = new Ass5Game();
        Main.game.initialize();
        Main.game.run();
    }

    /**
     * Gets the game instance.
     *
     * @return the game instance
     */
    public static Ass5Game getGame() {
        return Main.game;
    }
}
