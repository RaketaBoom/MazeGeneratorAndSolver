package backend.academy.maze.dto;

import backend.academy.maze.enums.GeneratorType;

public record CreateMazeRequestDTO(
    int height,
    int width,
    double earthProbability,
    boolean perfectFlag,
    GeneratorType generatorType
) {
}
