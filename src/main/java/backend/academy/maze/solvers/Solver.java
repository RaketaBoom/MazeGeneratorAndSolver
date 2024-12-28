package backend.academy.maze.solvers;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Solution;

public interface Solver {
    Solution solve(GraphMaze maze, Coordinate start, Coordinate end);
}
