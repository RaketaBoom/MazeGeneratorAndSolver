package backend.academy.maze.exceptions;

public class InvalidMazeSizeException extends RuntimeException{
    private static final String MESSAGE = "Недопустимые значения размеров лабиринта";

    public InvalidMazeSizeException(){
        super(MESSAGE);
    }
}
