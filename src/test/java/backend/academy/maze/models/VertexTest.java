package backend.academy.maze.models;

import backend.academy.maze.enums.Surface;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VertexTest {
    @Test
    void testFindEdge_RightEdge_ReturnsEdge() {
        // Arrange
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Edge expectedEdge = new Edge(v1, v2, Surface.EARTH);
        v1.right(expectedEdge);

        // Act
        Optional<Edge> actualEdge = v1.findEdge(v2);

        // Assert
        assertTrue(actualEdge.isPresent());
        assertEquals(expectedEdge, actualEdge.get());
    }

    @Test
    void testFindEdge_NoEdge_ReturnsEmpty() {
        // Arrange
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();

        // Act
        Optional<Edge> actualEdge = v1.findEdge(v2);

        // Assert
        assertTrue(actualEdge.isEmpty());
    }

    @Test
    void testFindAllEdges_ReturnsAllEdges() {
        // Arrange
        Vertex v1 = new Vertex();
        Edge rightEdge = new Edge(v1, new Vertex(), Surface.EARTH);
        Edge leftEdge = new Edge(v1, new Vertex(), Surface.MAGIC_STONES);
        v1.right(rightEdge);
        v1.left(leftEdge);

        // Act
        List<Edge> actualEdges = v1.findAllEdges();

        // Assert
        assertEquals(2, actualEdges.size());
        assertTrue(actualEdges.contains(rightEdge));
        assertTrue(actualEdges.contains(leftEdge));
    }

    @Test
    void testFindAllEdges_NoEdges_ReturnsEmptyList() {
        // Arrange
        Vertex v1 = new Vertex();

        // Act
        List<Edge> actualEdges = v1.findAllEdges();

        // Assert
        assertTrue(actualEdges.isEmpty());
    }
}
