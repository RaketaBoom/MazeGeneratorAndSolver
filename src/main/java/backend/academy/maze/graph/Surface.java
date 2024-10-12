package backend.academy.maze.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Surface {
    MAGIC_STONES(5, "$"),
    EARTH(10, " "),
    SWAMP(15, "#");

    private final int value;
    private final String symbol;
}
