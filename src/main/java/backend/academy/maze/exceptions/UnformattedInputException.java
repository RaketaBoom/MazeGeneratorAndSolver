package backend.academy.maze.exceptions;

public class UnformattedInputException extends RuntimeException {
    private static final String MESSAGE = "Не соовтествует формату ввода";

    public UnformattedInputException() {
        super(MESSAGE);
    }
}
