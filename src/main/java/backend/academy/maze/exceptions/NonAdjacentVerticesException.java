package backend.academy.maze.exceptions;

/**
 * Исключения для ситуации соединения несоседних вершин графа-лабиринта
 */
public class NonAdjacentVerticesException extends RuntimeException {
    private static final String MESSAGE = "Попытка связать несоседние вершины графа";

    public NonAdjacentVerticesException() {
        super(MESSAGE);
    }
}
