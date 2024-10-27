package backend.academy.maze.utils;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.surface.RandomSurfaceGenerator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MazeUtils {
    public static void makeGraphImperfect(GraphMaze graphMaze, int countOfWallsToRemove,
                                          RandomSurfaceGenerator surfaceGenerator, double earthProbability) {
        int countOfUndeletedWalls = countOfWallsToRemove;
        for (int i = 0; i < graphMaze.height(); i++) {
            for (int j = 0; j < graphMaze.width() - 1; j++) {
                Coordinate c1 = new Coordinate(i, j);
                Coordinate c2 = new Coordinate(i, j + 1);
                if (graphMaze.findEdge(c1, c2).isEmpty()) {
                    graphMaze.addEdge(c1, c2, surfaceGenerator.getSurface(earthProbability));
                    countOfUndeletedWalls--;
                    if (countOfUndeletedWalls <= 0) {
                        return;
                    }
                }
            }
        }
    }
}
