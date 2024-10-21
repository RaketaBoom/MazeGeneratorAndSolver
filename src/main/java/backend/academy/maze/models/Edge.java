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

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return surface == edge.surface;
    }

    @Override
    public int hashCode() {
        return surface != null ? surface.hashCode() : 0;
    }
}
