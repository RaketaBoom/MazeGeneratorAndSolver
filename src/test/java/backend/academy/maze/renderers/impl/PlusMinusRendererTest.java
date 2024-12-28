package backend.academy.maze.renderers.impl;

import backend.academy.maze.enums.Surface;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.renderers.Renderer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlusMinusRendererTest {
    static Renderer renderer;

    @BeforeAll
    public static void setup() {
        renderer = new PlusMinusRenderer();
    }

    @Test
    void testRender_CorrectGraph1() {
        // Arrange
        String expectedStringMaze = getExpectedStringMaze1();
        GraphMaze graphMaze = createMaze1();

        // Act
        String actualString = renderer.render(graphMaze);

        // Assert
        assertEquals(expectedStringMaze, actualString);
    }

    @Test
    void testRender_CorrectGraph2() {
        // Arrange
        String expectedStringMaze = getExpectedStringMaze2();
        GraphMaze graphMaze = createMaze2();

        // Act
        String actualString = renderer.render(graphMaze);

        // Assert
        assertEquals(expectedStringMaze, actualString);
    }

    @Test
    void testRender_CorrectGraph3() {
        // Arrange
        String expectedStringMaze = getExpectedStringMaze3();
        GraphMaze graphMaze = createMaze3();

        // Act
        String actualString = renderer.render(graphMaze);

        // Assert
        assertEquals(expectedStringMaze, actualString);
    }

    @Test
    void testRender_CorrectGraphAndPath(){
        // Arrange
        String expectedStringMaze = getExpectedStringMazeWithPath();
        GraphMaze graphMaze = createMaze2();
        List<Coordinate> path = getMazePath();

        // Act
        String actualString = renderer.render(graphMaze, path);

        // Assert
        assertEquals(expectedStringMaze, actualString);
    }

    private List<Coordinate> getMazePath() {
        return List.of(
            new Coordinate(0, 0),
            new Coordinate(0, 1),
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(2, 2),
            new Coordinate(2, 3),
            new Coordinate(3, 3),
            new Coordinate(4, 3),
            new Coordinate(4, 2),
            new Coordinate(3, 2)
        );
    }

    private GraphMaze createMaze1() {
        GraphMaze graphMaze = new GraphMaze(5, 4);
        graphMaze.addEdge(new Coordinate(0, 0), new Coordinate(0, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 1), new Coordinate(0, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 2), new Coordinate(0, 3), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 3), new Coordinate(1, 3), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 1), new Coordinate(1, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 1), new Coordinate(1, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 2), new Coordinate(2, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 2), new Coordinate(2, 3), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 3), new Coordinate(3, 3), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(3, 3), new Coordinate(4, 3), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(4, 3), new Coordinate(4, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(4, 2), new Coordinate(3, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(3, 2), new Coordinate(3, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(3, 1), new Coordinate(2, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 1), new Coordinate(2, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 0), new Coordinate(1, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 0), new Coordinate(3, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(3, 0), new Coordinate(4, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(4, 0), new Coordinate(4, 1), Surface.EARTH);
        return graphMaze;
    }

    private GraphMaze createMaze2() {
        GraphMaze graphMaze = new GraphMaze(5, 4);
        graphMaze.addEdge(new Coordinate(0, 0), new Coordinate(0, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 1), new Coordinate(0, 2), Surface.MAGIC_STONES);
        graphMaze.addEdge(new Coordinate(0, 2), new Coordinate(0, 3), Surface.MAGIC_STONES);
        graphMaze.addEdge(new Coordinate(0, 3), new Coordinate(1, 3), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(0, 1), new Coordinate(1, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 1), new Coordinate(1, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(1, 2), new Coordinate(2, 2), Surface.SWAMP);
        graphMaze.addEdge(new Coordinate(2, 2), new Coordinate(2, 3), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 3), new Coordinate(3, 3), Surface.SWAMP);
        graphMaze.addEdge(new Coordinate(3, 3), new Coordinate(4, 3), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(4, 3), new Coordinate(4, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(4, 2), new Coordinate(3, 2), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(3, 2), new Coordinate(3, 1), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(3, 1), new Coordinate(2, 1), Surface.MAGIC_STONES);
        graphMaze.addEdge(new Coordinate(2, 1), new Coordinate(2, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(2, 0), new Coordinate(1, 0), Surface.MAGIC_STONES);
        graphMaze.addEdge(new Coordinate(2, 0), new Coordinate(3, 0), Surface.EARTH);
        graphMaze.addEdge(new Coordinate(3, 0), new Coordinate(4, 0), Surface.SWAMP);
        graphMaze.addEdge(new Coordinate(4, 0), new Coordinate(4, 1), Surface.EARTH);
        return graphMaze;
    }

    private GraphMaze createMaze3() {
        return new GraphMaze(1, 1);
    }

    private String getExpectedStringMaze1() {
        return """
                 0   1   2   3
               +---------------+
            0  |               |
               +---+   +---+   |
            1  |   |       |   |
               |   +---+   +---+
            2  |       |       |
               |   +   +---+   |
            3  |   |       |   |
               |   +---+   +   |
            4  |       |       |
               +-------+-------+\
            """;
    }

    private String getExpectedStringMaze2() {
        return """
                 0   1   2   3
               +---------------+
            0  |       $   $   |
               +---+   +---+   |
            1  |   |       |   |
               | $ +---+ # +---+
            2  |       |       |
               |   + $ +---+ # |
            3  |   |       |   |
               | # +---+   +   |
            4  |       |       |
               +-------+-------+\
            """;
    }

    private String getExpectedStringMaze3() {
        return """
                 0
               +---+
            0  |   |
               +---+\
            """;
    }

    private String getExpectedStringMazeWithPath() {
        return """
                 0   1   2   3
               +---------------+
            0  | @   @ $   $   |
               +---+   +---+   |
            1  |   | @   @ |   |
               | $ +---+ # +---+
            2  |       | @   @ |
               |   + $ +---+ # |
            3  |   |     @ | @ |
               | # +---+   +   |
            4  |       | @   @ |
               +-------+-------+\
            """;
    }
}
