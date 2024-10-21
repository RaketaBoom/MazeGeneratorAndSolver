package backend.academy.maze.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {

    @Test
    void testAdd_SomeCoordinate_ReturnsCoordinate() {
        // Arrange
        Coordinate c1 = new Coordinate(3, 4);
        Coordinate c2 = new Coordinate(2, 2);
        Coordinate expectedCoordinate = new Coordinate(5, 6);

        // Act
        Coordinate actualCoordinate = c1.add(c2);

        // Assert
        assertEquals(expectedCoordinate, actualCoordinate);
    }
}
