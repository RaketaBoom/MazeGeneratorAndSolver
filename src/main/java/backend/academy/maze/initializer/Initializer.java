package backend.academy.maze.initializer;

import backend.academy.maze.enums.GeneratorType;
import backend.academy.maze.enums.SolverType;
import backend.academy.maze.generators.Generator;
import backend.academy.maze.generators.impl.BacktrackingMethod;
import backend.academy.maze.generators.impl.KruskalMethod;
import backend.academy.maze.solvers.Solver;
import backend.academy.maze.solvers.impl.AStarAlgorithm;
import backend.academy.maze.solvers.impl.DijkstraAlgorithm;
import backend.academy.maze.surface.RandomSurfaceGenerator;
import java.util.Random;
import lombok.experimental.UtilityClass;

/**
 * Класс с фабричными методами для создания генератора и солвера
 */
@UtilityClass
public class Initializer {
    /**
     * Принимает enum-объект тип генератора и объект random, вызвращает соответствующий типу генератор
     *
     * @param generatorType - тип генератора
     * @param random        - объект random
     * @return одну из реализаций Generator
     */
    public static Generator generator(GeneratorType generatorType, Random random) {
        return switch (generatorType) {
            case BACKTRACKING_METHOD -> new BacktrackingMethod(random, new RandomSurfaceGenerator(random));
            case KRUSKAL_METHOD -> new KruskalMethod(random, new RandomSurfaceGenerator(random));
        };
    }

    /**
     * Принимает enum-объект тип солвера, вызвращает соответствующий типу солвер
     *
     * @param solverType - тип солвера
     * @return одну из реализаций Solver
     */
    public static Solver solver(SolverType solverType) {
        return switch (solverType) {
            case DIJKSTRA -> new DijkstraAlgorithm();
            case A_STAR -> new AStarAlgorithm();
        };
    }
}
