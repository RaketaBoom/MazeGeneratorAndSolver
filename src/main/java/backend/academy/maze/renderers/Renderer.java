package backend.academy.maze.renderers;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import java.util.List;

public interface Renderer {
    String render(GraphMaze graphMaze);

    String render(GraphMaze graphMaze, List<Coordinate> path);
}
