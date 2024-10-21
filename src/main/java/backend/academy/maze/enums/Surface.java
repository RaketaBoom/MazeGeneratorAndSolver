package backend.academy.maze.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Содержит типы поверхностей проходов лабиринта
 * MAGIC_STONES - поверхность с улучшенной проходимостью
 * EARTH - поверхность с обычной проходимостью
 * SWAMP - поверхность с тяжелой проходимостью
 */
@AllArgsConstructor
@Getter
public enum Surface {
    MAGIC_STONES(1, "$"),
    EARTH(2, " "),
    SWAMP(3, "#");

    private final int value;
    private final String symbol;
}
