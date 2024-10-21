package backend.academy.maze.generators.impl;

import backend.academy.maze.enums.Surface;
import backend.academy.maze.exceptions.IllegalEarthProbabilityException;
import backend.academy.maze.exceptions.IllegalSizeValueException;
import backend.academy.maze.generators.Generator;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.surface.RandomSurfaceGenerator;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static backend.academy.maze.config.Config.EARTH_PROBABILITY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class BacktrackingMethodTest {
    Generator generator;
    @Mock
    Random mockRandom;
    @Mock
    RandomSurfaceGenerator mockSurfaceGenerator;

    @BeforeEach
    void setUp() {
        generator = new BacktrackingMethod(mockRandom, mockSurfaceGenerator);
    }

    void setupMocks() {
        Mockito.when(mockRandom.nextInt(anyInt())).thenReturn(0);
        Mockito.when(mockSurfaceGenerator.getSurface(EARTH_PROBABILITY)).thenReturn(Surface.EARTH);
    }

    @Test
    void testGenerate_InvalidHeight_ThrowsIllegalSizeValueException() {
        // Arrange
        int height = -1;
        int width = 2;
        boolean perfectFlag = true;

        // Act & Assert
        assertThrows(IllegalSizeValueException.class,
            () -> generator.generate(height, width, EARTH_PROBABILITY, perfectFlag));
    }

    @Test
    void testGenerate_InvalidEarthProbability_ThrowsIllegalEarthProbabilityException() {
        // Arrange
        int height = 5;
        int width = 2;
        boolean perfectFlag = true;
        double earthProbability = 3;

        // Act & Assert
        assertThrows(IllegalEarthProbabilityException.class,
            () -> generator.generate(height, width, earthProbability, perfectFlag));
    }

    @Test
    void testGenerate_perfectFlagTrue_ReturnsPerfectMaze() {
        // Arrange
        setupMocks();
        int height = 3;
        int width = 3;
        boolean perfectFlag = true;
        GraphMaze expectedGraphMaze = createPerfectMaze();

        // Act
        GraphMaze actualGraphMaze = generator.generate(height, width, EARTH_PROBABILITY, perfectFlag);

        // Assert
        assertEquals(expectedGraphMaze, actualGraphMaze);
    }

    @Test
    void testGenerate_perfectFlagFalse_ReturnsImperfectMaze() {
        // Arrange
        setupMocks();
        int height = 3;
        int width = 3;
        boolean perfectFlag = false;
        GraphMaze expectedGraphMaze = createImperfectMaze();

        // Act
        GraphMaze actualGraphMaze = generator.generate(height, width, EARTH_PROBABILITY, perfectFlag);

        // Assert
        assertEquals(expectedGraphMaze, actualGraphMaze);
    }

    private GraphMaze createImperfectMaze() {
        GraphMaze graphMaze = new GraphMaze(3, 3);
        graphMaze.addEdge(new Coordinate(0, 0), new Coordinate(0, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 1), new Coordinate(0, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 2), new Coordinate(1, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 2), new Coordinate(2, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 2), new Coordinate(2, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 1), new Coordinate(2, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 0), new Coordinate(1, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 0), new Coordinate(1, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 1), new Coordinate(1, 2), Surface.EARTH);
        return graphMaze;
    }

    private GraphMaze createPerfectMaze() {
        GraphMaze graphMaze = new GraphMaze(3, 3);
        graphMaze.addEdge(new Coordinate(0, 0), new Coordinate(0, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 1), new Coordinate(0, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 2), new Coordinate(1, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 2), new Coordinate(2, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 2), new Coordinate(2, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 1), new Coordinate(2, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 0), new Coordinate(1, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 0), new Coordinate(1, 1), Surface.EARTH);
        return graphMaze;
    }
}
