package backend.academy.maze.exceptions;

/**
 * Исключение при попытке соединить вершину не входяющую граф
 */
public class VertexNotInGraphException extends RuntimeException {
    private static final String MESSAGE = "Вершина не принадлежит графу";

    public VertexNotInGraphException() {
        super(MESSAGE);
    }
}
