package backend.academy.maze.generators;

import backend.academy.maze.models.GraphMaze;

public interface Generator {
    GraphMaze generate(int height, int width, double earthProbability, boolean perfectFlag);
}
