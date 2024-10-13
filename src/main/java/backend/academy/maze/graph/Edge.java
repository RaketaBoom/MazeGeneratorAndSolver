package backend.academy.maze.graph;

import backend.academy.maze.surface.Surface;

public record Edge(
    Vertex v1,
    Vertex v2,
    Surface surface
) {
    public boolean contain(Vertex v){
        return v == v1 || v == v2;
    }
}
