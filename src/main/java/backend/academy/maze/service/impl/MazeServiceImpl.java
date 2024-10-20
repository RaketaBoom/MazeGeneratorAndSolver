package backend.academy.maze.service.impl;

import backend.academy.maze.initializer.Initializer;
import backend.academy.maze.dto.CreateMazeRequestDTO;
import backend.academy.maze.dto.SolveMazeRequestDTO;
import backend.academy.maze.generators.Generator;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Solution;
import backend.academy.maze.service.MazeService;
import backend.academy.maze.solvers.Solver;
import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MazeServiceImpl implements MazeService {
    private final Random random;

    @Override
    public GraphMaze createMaze(CreateMazeRequestDTO createMazeRequestDTO) {
        Generator generator = Initializer.generator(createMazeRequestDTO.generatorType(), random);
        return generator.generate(
            createMazeRequestDTO.height(),
            createMazeRequestDTO.width(),
            createMazeRequestDTO.earthProbability(),
            createMazeRequestDTO.perfectFlag()
        );
    }

    @Override
    public Solution solveMaze(SolveMazeRequestDTO solveMazeRequestDTO) {
        Solver solver = Initializer.solver(solveMazeRequestDTO.solverType());
        return solver.solve(
            solveMazeRequestDTO.graphMaze(),
            solveMazeRequestDTO.startPoint(),
            solveMazeRequestDTO.endPoint()
        );
    }
}
