package backend.academy.maze.dto;

import backend.academy.maze.enums.GeneratorType;

/**
 * DTO для запроса генерации лабиринта
 * @param height - высота лабиринта
 * @param width - ширина лабиринта
 * @param earthProbability - вероятность проходов с землянной поверхностью
 * @param perfectFlag - флаг, отвечающий за создание идеального лабиринта
 * @param generatorType - способ генерации лабиринта
 */
public record CreateMazeRequestDTO(
    int height,
    int width,
    double earthProbability,
    boolean perfectFlag,
    GeneratorType generatorType
) {
}
