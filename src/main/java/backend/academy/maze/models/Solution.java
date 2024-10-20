package backend.academy.maze.models;

import java.util.List;

public record Solution(
    List<Coordinate> path,
    int distance
) {
}
