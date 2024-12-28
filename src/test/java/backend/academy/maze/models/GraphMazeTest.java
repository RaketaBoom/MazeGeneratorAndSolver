package backend.academy.maze.models;

import backend.academy.maze.enums.Surface;
import backend.academy.maze.exceptions.IllegalCoordinateValueException;
import backend.academy.maze.exceptions.NonAdjacentVerticesException;
import backend.academy.maze.exceptions.SelfLoopException;
import backend.academy.maze.exceptions.VertexNotInGraphException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphMazeTest {
    GraphMaze graphMaze;
    Vertex v1, v2;
    Surface surface;

    @BeforeEach
    void setUp() {
        graphMaze = new GraphMaze(3, 3); // создаем лабиринт 3x3
        v1 = new Vertex();
        v2 = new Vertex();
        surface = Surface.EARTH; // Пример значения Surface
    }

    @Test
    void testContainVertex_VertexInGraph_ReturnsTrue() {
        // Arrange
        Vertex vertexInGraph = graphMaze.getVertex(new Coordinate(0, 0));

        // Act
        boolean result = graphMaze.containVertex(vertexInGraph);

        // Assert
        assertTrue(result);
    }

    @Test
    void testContainVertex_VertexNotInGraph_ReturnsFalse() {
        // Act
        boolean result = graphMaze.containVertex(v1);

        // Assert
        assertFalse(result);
    }

    @Test
    void testFindEdge_TwoVerticesWithEdge_ReturnsEdge() {
        // Arrange
        graphMaze.addEdge(new Coordinate(0, 0), new Coordinate(0, 1), surface);

        // Act
        Optional<Edge> edge = graphMaze.findEdge(
            graphMaze.getVertex(new Coordinate(0, 0)),
            graphMaze.getVertex(new Coordinate(0, 1))
        );

        // Assert
        assertTrue(edge.isPresent());
    }

    @Test
    void testFindEdge_TwoVerticesWithoutEdge_ReturnsEmpty() {
        // Act
        Optional<Edge> edge = graphMaze.findEdge(
            graphMaze.getVertex(new Coordinate(0, 0)),
            graphMaze.getVertex(new Coordinate(1, 1))
        );

        // Assert
        assertFalse(edge.isPresent());
    }

    @Test
    void testGetVertex_ValidCoordinate_ReturnsVertex() {
        // Act
        Vertex vertex = graphMaze.getVertex(new Coordinate(1, 1));

        // Assert
        assertNotNull(vertex);
    }

    @Test
    void testGetVertex_InvalidCoordinate_ThrowsIllegalCoordinateValueException() {
        // Arrange
        Coordinate c1 = new Coordinate(3, 3);

        // Act & Assert
        assertThrows(IllegalCoordinateValueException.class,
            () -> graphMaze.getVertex(c1));
    }

    @Test
    void testGetCoordinateOfVertex_VertexInGraph_ReturnsCoordinate() {
        // Arrange
        Vertex vertexInGraph = graphMaze.getVertex(new Coordinate(1, 1));

        // Act
        Coordinate coordinate = graphMaze.getCoordinateOfVertex(vertexInGraph);

        // Assert
        assertEquals(new Coordinate(1, 1), coordinate);
    }

    @Test
    void testGetCoordinateOfVertex_VertexNotInGraph_ThrowsVertexNotInGraphException() {
        // Act & Assert
        assertThrows(VertexNotInGraphException.class,
            () -> graphMaze.getCoordinateOfVertex(v1));
    }

    @Test
    void testAddEdge_ValidAdjacentVertices_AddsEdge() {
        // Act
        Edge edge = graphMaze.addEdge(new Coordinate(0, 0), new Coordinate(0, 1), surface);

        // Assert
        assertNotNull(edge);
        assertTrue(graphMaze.findEdge(new Coordinate(0, 0), new Coordinate(0, 1)).isPresent());
    }

    @Test
    void testAddEdge_SelfLoop_ThrowsSelfLoopException() {
        // Arrange
        Coordinate c1 = new Coordinate(0, 0);
        Coordinate c2 = new Coordinate(0, 0);

        // Act & Assert
        assertThrows(SelfLoopException.class,
            () -> graphMaze.addEdge(c1, c2, surface));
    }

    @Test
    void testAddEdge_NonAdjacentVertices_ThrowsNonAdjacentVerticesException() {
        // Arrange
        Coordinate c1 = new Coordinate(0, 0);
        Coordinate c2 = new Coordinate(2, 2);

        // Act & Assert
        assertThrows(NonAdjacentVerticesException.class,
            () -> graphMaze.addEdge(c1, c2, surface));
    }
}
