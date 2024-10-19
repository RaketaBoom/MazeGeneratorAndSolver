package backend.academy.maze.solvers;

import backend.academy.maze.graph.Coordinate;
import backend.academy.maze.graph.GraphMaze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(GraphMaze maze, Coordinate start, Coordinate end);
}
