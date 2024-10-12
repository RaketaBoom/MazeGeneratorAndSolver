package backend.academy.maze.exceptions;

public class VertexNotInGraphException extends RuntimeException{
    private final static String MESSAGE = "Вершина не принадлежит графу";

    public VertexNotInGraphException() {
        super(MESSAGE);
    }
}
