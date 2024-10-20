package backend.academy.maze.initializer;

import backend.academy.maze.enums.GeneratorType;
import backend.academy.maze.enums.SolverType;
import backend.academy.maze.generators.Generator;
import backend.academy.maze.generators.impl.BacktrackingMethod;
import backend.academy.maze.generators.impl.KruskalMethod;
import backend.academy.maze.solvers.Solver;
import backend.academy.maze.solvers.impl.DijkstraAlgorithm;
import backend.academy.maze.surface.RandomSurfaceGenerator;
import lombok.experimental.UtilityClass;
import java.util.Random;

@UtilityClass
public class Initializer {
    public static Generator generator(GeneratorType generatorType, Random random) {
        return switch (generatorType) {
            case BACKTRACKING_METHOD -> new BacktrackingMethod(random, new RandomSurfaceGenerator(random));
            case KRUSKAL_METHOD -> new KruskalMethod(random, new RandomSurfaceGenerator(random));
        };
    }

    public static Solver solver(SolverType solverType) {
        return switch (solverType) {
            case DIJKSTRA -> new DijkstraAlgorithm();
        };
    }
}
