package backend.academy.maze.solvers.impl;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Solution;
import backend.academy.maze.solvers.Solver;
import java.util.Comparator;
import java.util.HashMap;

public class AStarAlgorithm extends SolverAlgorithm implements Solver {

    /**
     * А* алгоритм
     * По сути, взял лучшее от Дейкстры и от жадного алгоритма тем, что мы по-другому выбираем следующую вершину,
     * т.е. по-иному заполняем приоритетную очередь. При записи в приоритетную очередь, компаратор должен учитывать
     * расстояние до начальной точки и Манхетенское расстояние до финишной.
     */
    @Override
    public Solution solve(GraphMaze maze, Coordinate start, Coordinate end) {
        distances = new HashMap<>();
        Comparator<Coordinate> comparator = Comparator.comparingInt(c -> distances.get(c) + manhattanDistance(c, end));

        return launch(maze, start, end, comparator);
    }

    private int manhattanDistance(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.col() - c2.col()) + Math.abs(c1.row() - c2.row());
    }
}
