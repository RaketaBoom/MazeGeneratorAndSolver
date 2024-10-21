package backend.academy.maze.config;

import lombok.experimental.UtilityClass;

/**
 * Класс конфигурации приложения
 * MAX_HEIGHT - максимально допустимая высота лабиринта
 * MAX_WIDTH - максимально допустимая ширина лабиринта
 * EARTH_PROBABILITY - вероятность появления земляной поверхности (пустых проходов)
 * Все значения можно изменять и настраивать под себя
 */
@UtilityClass
public class Config {
    public static final int MAX_HEIGHT = 20;
    public static final int MAX_WIDTH = 20;
    public static final double EARTH_PROBABILITY = 0.75;
}
