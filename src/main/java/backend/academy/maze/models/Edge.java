package backend.academy.maze.models;

import backend.academy.maze.enums.Surface;

public record Edge(
    Vertex v1,
    Vertex v2,
    Surface surface
) {
    public boolean contain(Vertex v) {
        return v == v1 || v == v2;
    }
}
