package backend.academy.maze.exceptions;

public class NonAdjacentVerticesException extends RuntimeException{
    private static final String MESSAGE = "Попытка связать несоседние вершины графа";

    public NonAdjacentVerticesException() {
        super(MESSAGE);
    }
}
