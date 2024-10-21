package backend.academy.maze.models;

import backend.academy.maze.enums.Surface;
import backend.academy.maze.exceptions.IllegalCoordinateValueException;
import backend.academy.maze.exceptions.IllegalSizeValueException;
import backend.academy.maze.exceptions.NonAdjacentVerticesException;
import backend.academy.maze.exceptions.SelfLoopException;
import backend.academy.maze.exceptions.VertexNotInGraphException;
import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;

@Getter
public class GraphMaze {
    private final int height;
    private final int width;
    private final Vertex[][] graph;

    public GraphMaze(int height, int width) {
        if (height < 1 || width < 1) {
            throw new IllegalSizeValueException();
        }
        this.height = height;
        this.width = width;
        this.graph = generateEmptyGraph(height, width);
    }

    private Vertex[][] generateEmptyGraph(int height, int width) {
        Vertex[][] matrix = new Vertex[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = new Vertex();
            }
        }
        return matrix;
    }

    public boolean containVertex(Vertex v) {
        return Arrays.stream(graph)
            .flatMap(Arrays::stream)
            .anyMatch(x -> x == v);
    }

    public Optional<Edge> findEdge(Vertex v1, Vertex v2) {
        if (!containVertex(v1) && containVertex(v2)) {
            return Optional.empty();
        }
        return v1.findEdge(v2);
    }

    public Optional<Edge> findEdge(Coordinate c1, Coordinate c2) {
        checkCoordinate(c1);
        checkCoordinate(c2);
        return findEdge(getVertex(c1), getVertex(c2));
    }

    private void checkCoordinate(Coordinate c) {
        if (!isCoordinateInBounds(c)){
            throw new IllegalCoordinateValueException();
        }
    }

    private boolean isCoordinateInBounds(Coordinate coordinate) {
        return coordinate.row() >= 0 && coordinate.row() < height
            && coordinate.col() >= 0 && coordinate.col() < width;
    }

    public Vertex getVertex(Coordinate c) {
        if ((c.row() >= height || c.row() < 0)
            && (c.col() >= width || c.col() < 0)) {
            throw new IllegalCoordinateValueException();
        }
        return graph[c.row()][c.col()];
    }

    public Coordinate getCoordinateOfVertex(Vertex v) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (graph[i][j] == v) {
                    return new Coordinate(i, j);
                }
            }
        }
        throw new VertexNotInGraphException();
    }

    public Edge addEdge(Coordinate c1, Coordinate c2, Surface surface) {
        if (c1.equals(c2)) {
            throw new SelfLoopException();
        }
        if (Math.abs(c1.col() - c2.col()) + Math.abs(c1.row() - c2.row()) > 1) {
            throw new NonAdjacentVerticesException();
        }
        Vertex v1 = getVertex(c1);
        Vertex v2 = getVertex(c2);
        Edge edge = new Edge(v1, v2, surface);

        if (c1.col() < c2.col()) {
            v1.right(edge);
            v2.left(edge);
        }
        if (c1.col() > c2.col()) {
            v1.left(edge);
            v2.right(edge);
        }

        if (c1.row() < c2.row()) {
            v1.up(edge);
            v2.dawn(edge);
        }
        if (c1.row() > c2.row()) {
            v1.dawn(edge);
            v2.up(edge);
        }
        return edge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphMaze graphMaze = (GraphMaze) o;

        if (height != graphMaze.height) return false;
        if (width != graphMaze.width) return false;
        return Arrays.deepEquals(graph, graphMaze.graph);
    }

    @Override
    public int hashCode() {
        int result = height;
        result = 31 * result + width;
        result = 31 * result + Arrays.deepHashCode(graph);
        return result;
    }
}
