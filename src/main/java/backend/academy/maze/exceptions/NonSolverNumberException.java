package backend.academy.maze.exceptions;

public class NonSolverNumberException extends RuntimeException {
    private static final String MESSAGE = "Ввод не соответствует типу алгоритма решения";

    public NonSolverNumberException() {
        super(MESSAGE);
    }
}
