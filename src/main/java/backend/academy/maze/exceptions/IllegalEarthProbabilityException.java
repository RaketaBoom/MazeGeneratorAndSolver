package backend.academy.maze.exceptions;

public class IllegalEarthProbabilityException extends RuntimeException{
    private static final String MESSAGE = "Неправильное значение координат";

    public IllegalEarthProbabilityException() {
        super(MESSAGE);
    }
}
