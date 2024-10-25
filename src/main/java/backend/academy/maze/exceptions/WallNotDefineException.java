package backend.academy.maze.exceptions;

/**
 * Исключение сообщающее, что невозможно определить тип стены
 */
public class WallNotDefineException extends RuntimeException {
    private static final String MESSAGE = "Невозможно определить тип стены";

    public WallNotDefineException() {
        super(MESSAGE);
    }
}
