package backend.academy.maze.solvers.impl;

import backend.academy.maze.enums.Surface;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Solution;
import backend.academy.maze.solvers.Solver;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraAlgorithmTest {
    Solver solver = new DijkstraAlgorithm();

    @Test
    void testSolve_GraphMazeWithoutEdges_ReturnsSolutionWithEmptyListAndZeroDistance() {
        // Arrange
        GraphMaze graphMaze = new GraphMaze(5, 5);
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);
        Solution expectedSolution = new Solution(List.of(), 0);

        // Act
        Solution actualSolution = solver.solve(graphMaze, start, end);

        // Assert
        assertEquals(expectedSolution, actualSolution);
    }

    @Test
    void testSolve_GraphMaze_ReturnSolution() {
        // Arrange
        GraphMaze graphMaze = createMaze();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 1);
        Solution expectedSolution = getSolution1();

        // Act
        Solution actualSolution = solver.solve(graphMaze, start, end);

        // Assert
        assertEquals(expectedSolution, actualSolution);
    }

    private GraphMaze createMaze() {
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
        graphMaze.addEdge(new Coordinate(0, 0), new Coordinate(1, 0), Surface.EARTH);
        return graphMaze;
    }

    private Solution getSolution1() {
        List<Coordinate> path = List.of(
            new Coordinate(0, 0),
            new Coordinate(1, 0),
            new Coordinate(2, 0),
            new Coordinate(2, 1),
            new Coordinate(3, 1)
        );
        int distance = 6;

        return new Solution(path, distance);
    }
}
