package backend.academy.maze.exceptions;

/**
 * Исключение для недопустимых значений размера:
 * - размер задается отрицательными числами
 * - размер задается нулевыми высотой или шириной
 */
public class IllegalSizeValueException extends RuntimeException {
    private static final String MESSAGE = "Недопустимое значение размера лабиринта";

    public IllegalSizeValueException() {
        super(MESSAGE);
    }
}
