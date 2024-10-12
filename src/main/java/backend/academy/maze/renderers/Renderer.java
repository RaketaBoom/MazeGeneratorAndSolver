package backend.academy.maze.renderers;

import backend.academy.maze.graph.GraphMaze;

public interface Renderer {
    String render(GraphMaze graphMaze);
//    String render(Maze maze, List<Coordinate> path);
}
