package backend.academy.maze.exceptions;

/**
 * Исключение для неправильного значения вероятности появления землянной поверхности:
 * - значение меньше 0
 * - значение больше 1
 */
public class IllegalEarthProbabilityException extends RuntimeException {
    private static final String MESSAGE = "Неправильное значение вероятности";

    public IllegalEarthProbabilityException() {
        super(MESSAGE);
    }
}
