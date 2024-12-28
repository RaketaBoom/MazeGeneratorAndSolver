package backend.academy.maze.exceptions;

/**
 * Исключение для неформатированного ввода
 */
public class UnformattedInputException extends RuntimeException {
    private static final String MESSAGE = "Не соовтествует формату ввода";

    public UnformattedInputException() {
        super(MESSAGE);
    }
}
