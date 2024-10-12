package backend.academy.maze.exceptions;

public class IllegalCoordinateValueException extends RuntimeException {
    private static final String MESSAGE = "Неправильное значение координат";

    public IllegalCoordinateValueException() {
        super(MESSAGE);
    }
}
