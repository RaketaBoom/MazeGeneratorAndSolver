package backend.academy.maze.exceptions;

public class NonGeneratorNumberException extends RuntimeException {
    private static final String MESSAGE = "Ввод не соответствует типу генератора";

    public NonGeneratorNumberException() {
        super(MESSAGE);
    }
}
