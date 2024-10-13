package backend.academy.maze.exceptions;

public class IllegalProbabilityValueException extends RuntimeException{
    private static final String MESSAGE = "Неправильное значение координат";

    public IllegalProbabilityValueException() {
        super(MESSAGE);
    }
}
