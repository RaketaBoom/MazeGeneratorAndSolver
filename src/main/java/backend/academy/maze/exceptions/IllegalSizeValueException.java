package backend.academy.maze.exceptions;

public class IllegalSizeValueException extends RuntimeException {
    private static final String MESSAGE = "Недопустимое значение размера лабиринта";

    public IllegalSizeValueException() {
        super(MESSAGE);
    }
}
