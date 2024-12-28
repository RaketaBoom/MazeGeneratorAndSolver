package backend.academy.maze.exceptions;

/**
 * При попытке обратиться к несуществующему ребру
 */
public class EdgeNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Несуществующее ребро";

    public EdgeNotFoundException() {
        super(MESSAGE);
    }
}
