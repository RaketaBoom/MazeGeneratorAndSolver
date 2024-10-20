package backend.academy.maze.generators;

import backend.academy.maze.models.GraphMaze;

public interface Generator {
    public static final double MAX_EARTH_PROBABILITY = 1;
    GraphMaze generate(int height, int width, double earthProbability, boolean perfectFlag);
}
