package backend.academy.maze.exceptions;

/**
 * Исключение для неправильного значения координат:
 * - координата в отрицательной плоскости
 * - координата выходит за пределы лабиринта
 */
public class IllegalCoordinateValueException extends RuntimeException {
    private static final String MESSAGE = "Неправильное значение координат";

    public IllegalCoordinateValueException() {
        super(MESSAGE);
    }
}
