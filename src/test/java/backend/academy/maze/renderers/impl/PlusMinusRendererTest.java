package backend.academy.maze.renderers.impl;

import backend.academy.maze.graph.Coordinate;
import backend.academy.maze.graph.GraphMaze;
import backend.academy.maze.graph.Surface;
import backend.academy.maze.renderers.Renderer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlusMinusRendererTest {
    static Renderer renderer;

    @BeforeAll
    public static void setup(){
        renderer = new PlusMinusRenderer();
    }

    @Test
    void Render_GraphProvided_CorrectStringReturned() {
        //Arrange
        String expendStringMaze = getExpendStringMaze1();
        GraphMaze graphMaze = createMaze1();

        //Act
        String actualString = renderer.render(graphMaze);

        //Assert
        assertEquals(expendStringMaze, actualString);
    }

    private GraphMaze createMaze1(){
        GraphMaze graphMaze1 = new GraphMaze(5, 4);
        graphMaze1.addEdge(new Coordinate(0, 0), new Coordinate(0, 1), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(0, 1), new Coordinate(0, 2), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(0, 2), new Coordinate(0, 3), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(0, 3), new Coordinate(1, 3), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(0, 1), new Coordinate(1, 1), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(1, 1), new Coordinate(1, 2), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(1, 2), new Coordinate(2, 2), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(2, 2), new Coordinate(2, 3), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(2, 3), new Coordinate(3, 3), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(3, 3), new Coordinate(4, 3), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(4, 3), new Coordinate(4, 2), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(4, 2), new Coordinate(3, 2), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(3, 2), new Coordinate(3, 1), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(3, 1), new Coordinate(2, 1), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(2, 1), new Coordinate(2, 0), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(2, 0), new Coordinate(1, 0), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(2, 0), new Coordinate(3, 0), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(3, 0), new Coordinate(4, 0), Surface.EARTH);
        graphMaze1.addEdge(new Coordinate(4, 0), new Coordinate(4, 1), Surface.EARTH);
        return graphMaze1;
    }

    private String getExpendStringMaze1(){
        return """
            +---------------+
            |               |
            +---+   +---+   |
            |   |       |   |
            |   +---+   +---+
            |       |       |
            |   +   +---+   |
            |   |       |   |
            |   +---+   +   |
            |       |       |
            +-------+-------+
            """;
    }
}
