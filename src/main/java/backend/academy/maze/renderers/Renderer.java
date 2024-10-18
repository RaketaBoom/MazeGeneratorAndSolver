package backend.academy.maze.renderers;

import backend.academy.maze.graph.Coordinate;
import backend.academy.maze.graph.GraphMaze;
import java.util.List;

public interface Renderer {
    String render(GraphMaze graphMaze);
    String render(GraphMaze graphMaze, List<Coordinate> path);
}
