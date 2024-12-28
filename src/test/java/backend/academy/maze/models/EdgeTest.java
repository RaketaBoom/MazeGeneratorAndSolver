package backend.academy.maze.models;

import backend.academy.maze.enums.Surface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EdgeTest {

    @Test
    void testEquals_Symmetric() {
        //Arrange
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Surface surface = Surface.SWAMP;
        Edge e1 = new Edge(v1, v2, surface);
        Edge e2 = new Edge(v1, v2, surface);

        //Act & Assert
        assertEquals(e1, e2, "Ребра с одинаковой поверхностью должны быть равны");
        assertEquals(e2, e1, "Проверка симметричности equals()");
    }

    @Test
    void testEquals_DifferentSurface() {
        //Arrange
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Edge e1 = new Edge(v1, v2, Surface.SWAMP);
        Edge e2 = new Edge(v1, v2, Surface.EARTH);

        //Act & Assert
        assertNotEquals(e1, e2, "Ребра с разными поверхностями не должны быть равны");
    }

    @Test
    void testHashCode_EqualEdges() {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Surface surface = Surface.SWAMP;

        Edge e1 = new Edge(v1, v2, surface);
        Edge e2 = new Edge(v1, v2, surface);

        assertEquals(e1.hashCode(), e2.hashCode(),
            "Ребра с одинаковыми поверхностями должны иметь одинаковый hashCode()");
    }

    @Test
    void testHashCode_DifferentSurfaces() {
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();

        Edge e1 = new Edge(v1, v2, Surface.SWAMP);
        Edge e2 = new Edge(v1, v2, Surface.EARTH);

        assertNotEquals(e1.hashCode(), e2.hashCode(),
            "Ребра с разными поверхностями должны иметь разные hashCode()");
    }

    @Test
    void testContainVertex_ContainedVertices_ReturnsTrue() {
        // Arrange
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Edge edge = new Edge(v1, v2, Surface.SWAMP);

        // Act & Assert
        assertTrue(edge.contain(v1));
        assertTrue(edge.contain(v2));
    }

    @Test
    void testContainVertex_NonContainedVertices_ReturnsFalse() {
        // Arrange
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Vertex v3 = new Vertex();
        Edge edge = new Edge(v1, v2, Surface.SWAMP);

        // Act & Assert
        assertFalse(edge.contain(v3));
    }
}
