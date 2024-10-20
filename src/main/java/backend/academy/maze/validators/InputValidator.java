package backend.academy.maze.validators;

import backend.academy.maze.enums.GeneratorType;
import backend.academy.maze.enums.SolverType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Size;
import lombok.Setter;
import static backend.academy.maze.config.Config.MAX_HEIGHT;
import static backend.academy.maze.config.Config.MAX_WIDTH;

@Setter
public class InputValidator {
    private Size currSize;

    public boolean isGeneratorNumber(String t) {
        if (t.length() != 1) {
            return false;
        }
        return t.charAt(0) >= '1' && t.charAt(0) <= '0' + GeneratorType.values().length;
    }

    public boolean containsTwoNumbers(String input) {
        return input.matches("\\d+\\s+\\d+");
    }

    public boolean isCorrectSize(Size size) {
        return size.height() > 0 && size.height() <= MAX_HEIGHT
            && size.width() > 0 && size.width() <= MAX_WIDTH;
    }

    public boolean isCorrectCoordinate(Coordinate coordinate) {
        return coordinate.row() >= 0 && coordinate.row() < currSize.height()
            && coordinate.col() >= 0 && coordinate.col() < currSize.width();
    }

    public boolean isYesOrNoAnswer(String input) {
        return input.equals("да") || input.equals("нет");
    }

    public boolean isSolverNumber(String t) {
        if (t.length() != 1) {
            return false;
        }
        return t.charAt(0) >= '1' && t.charAt(0) <= '0' + SolverType.values().length;
    }
}
