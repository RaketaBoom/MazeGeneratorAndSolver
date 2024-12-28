package backend.academy.maze.dto;

import backend.academy.maze.enums.SolverType;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;

/**
 * DTO для запроса поиска решения лабиринта
 *
 * @param graphMaze  - граф лабиринта
 * @param startPoint - координаты стартовой точки
 * @param endPoint   - координаты конечной точки
 * @param solverType - способ нахождения решения лабиринта
 */
public record SolveMazeRequestDTO(
    GraphMaze graphMaze,
    Coordinate startPoint,
    Coordinate endPoint,
    SolverType solverType
) {
}
