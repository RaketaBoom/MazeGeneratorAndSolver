package backend.academy.maze.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Surface {
    MAGIC_STONES(1, "$"),
    EARTH(2, " "),
    SWAMP(3, "#");

    private final int value;
    private final String symbol;
}
