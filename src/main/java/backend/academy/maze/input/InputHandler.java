package backend.academy.maze.input;

import backend.academy.maze.enums.GeneratorType;
import backend.academy.maze.enums.SolverType;
import backend.academy.maze.exceptions.IllegalCoordinateValueException;
import backend.academy.maze.exceptions.IllegalSizeValueException;
import backend.academy.maze.exceptions.NonGeneratorNumberException;
import backend.academy.maze.exceptions.UnformattedInputException;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Size;
import backend.academy.maze.validators.InputValidator;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InputHandler {
    private final Scanner scanner;
    private final InputValidator inputValidator;

    public GeneratorType getGeneratorType() {
        String input = scanner.nextLine();
        if (!inputValidator.isGeneratorNumber(input) && !input.isEmpty()) {
            throw new NonGeneratorNumberException();
        }

        int value = Character.getNumericValue(input.charAt(0));

        return GeneratorType.valueOf(value);
    }

    public Size getSize() {
        String input = scanner.nextLine();
        if (!inputValidator.containsTwoNumbers(input)) {
            throw new UnformattedInputException();
        }
        String[] parts = input.trim().split("\\s+");
        int height = Integer.parseInt(parts[0]);
        int width = Integer.parseInt(parts[1]);

        Size size = new Size(height, width);
        if (!inputValidator.isCorrectSize(size)) {
            throw new IllegalSizeValueException();
        }
        inputValidator.currSize(size);
        return size;
    }

    public Coordinate getCoordinate() {
        String input = scanner.nextLine();
        if (!inputValidator.containsTwoNumbers(input)) {
            throw new UnformattedInputException();
        }
        String[] parts = input.trim().split("\\s+");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        Coordinate coordinate = new Coordinate(row, col);
        if (!inputValidator.isCorrectCoordinate(coordinate)) {
            throw new IllegalCoordinateValueException();
        }
        return coordinate;
    }

    public boolean getPerfectFlag() {
        String input = scanner.nextLine();
        if (!inputValidator.isYesOrNoAnswer(input)) {
            throw new UnformattedInputException();
        }
        return input.equals("да");
    }

    public SolverType getSolverType() {
        String input = scanner.nextLine();
        if (!inputValidator.isSolverNumber(input) && !input.isEmpty()) {
            throw new NonGeneratorNumberException();
        }

        int value = Character.getNumericValue(input.charAt(0));

        return SolverType.valueOf(value);
    }

}
