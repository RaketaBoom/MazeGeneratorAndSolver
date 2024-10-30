package backend.academy.maze;

import backend.academy.maze.dto.CreateMazeRequestDTO;
import backend.academy.maze.dto.SolveMazeRequestDTO;
import backend.academy.maze.enums.GeneratorType;
import backend.academy.maze.enums.SolverType;
import backend.academy.maze.input.InputHandler;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Size;
import backend.academy.maze.models.Solution;
import backend.academy.maze.output.ConsoleDisplay;
import backend.academy.maze.renderers.Renderer;
import backend.academy.maze.renderers.impl.PlusMinusRenderer;
import backend.academy.maze.service.MazeService;
import backend.academy.maze.service.impl.MazeServiceImpl;
import backend.academy.maze.validators.InputValidator;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import static backend.academy.maze.constants.Constants.EARTH_PROBABILITY;

public class MazeApi {
    private final InputHandler input;
    private final ConsoleDisplay display;
    private final Random random = new Random();
    private final Renderer renderer = new PlusMinusRenderer();

    public MazeApi(InputStream inputStream, PrintStream outputStream) {
        this.input = new InputHandler(new Scanner(inputStream), new InputValidator());
        this.display = new ConsoleDisplay(outputStream);
    }

    public void start() {
        GeneratorType generatorType = requestGeneratorType();
        boolean perfectFlag = requestPerfectFlag();
        Size size = requestSize();
        CreateMazeRequestDTO createMazeRequestDTO = new CreateMazeRequestDTO(
            size.height(),
            size.width(),
            EARTH_PROBABILITY,
            perfectFlag,
            generatorType
        );

        MazeService mazeService = new MazeServiceImpl(random);
        GraphMaze graphMaze = mazeService.createMaze(createMazeRequestDTO);
        display.maze(renderer.render(graphMaze));

        Coordinate start = requestStartCoordinate();
        Coordinate end = requestEndCoordinate();
        SolverType solverType = requestSolverType();

        SolveMazeRequestDTO solveMazeRequestDTO = new SolveMazeRequestDTO(
            graphMaze,
            start,
            end,
            solverType
        );

        Solution solution = mazeService.solveMaze(solveMazeRequestDTO);
        String stringMaze = renderer.render(graphMaze, solution.path());
        display.solution(stringMaze, solution.distance());
    }

    private SolverType requestSolverType() {
        while (true) {
            try {
                display.choiceSolver();
                return input.getSolverType();
            } catch (RuntimeException e) {
                display.errorMessage(e.getMessage());
            }
        }
    }

    private Coordinate requestEndCoordinate() {
        while (true) {
            try {
                display.enterEndPoint();
                return input.getCoordinate();
            } catch (RuntimeException e) {
                display.errorMessage(e.getMessage());
            }
        }
    }

    private Coordinate requestStartCoordinate() {
        while (true) {
            try {
                display.enterStartPoint();
                return input.getCoordinate();
            } catch (RuntimeException e) {
                display.errorMessage(e.getMessage());
            }
        }
    }

    private Size requestSize() {
        while (true) {
            try {
                display.sizeOfMaze();
                return input.getSize();
            } catch (RuntimeException e) {
                display.errorMessage(e.getMessage());
            }
        }
    }

    private boolean requestPerfectFlag() {
        while (true) {
            try {
                display.choicePerfectFlag();
                return input.getPerfectFlag();
            } catch (RuntimeException e) {
                display.errorMessage(e.getMessage());
            }
        }
    }

    private GeneratorType requestGeneratorType() {
        while (true) {
            try {
                display.choiceGeneratorType();
                return input.getGeneratorType();
            } catch (RuntimeException e) {
                display.errorMessage(e.getMessage());
            }
        }
    }
}
