package backend.academy.maze.dto;

import backend.academy.maze.enums.SolverType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;

public record SolveMazeRequestDTO(
    GraphMaze graphMaze,
    Coordinate startPoint,
    Coordinate endPoint,
    SolverType solverType
) {
}
