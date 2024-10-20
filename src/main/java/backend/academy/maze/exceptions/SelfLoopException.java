package backend.academy.maze.exceptions;

public class SelfLoopException extends RuntimeException {
    private static final String MESSAGE = "Нельзя создавать петли в графе";

    public SelfLoopException() {
        super(MESSAGE);
    }
}
