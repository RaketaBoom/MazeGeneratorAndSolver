package backend.academy.maze.generators.impl;

import backend.academy.maze.exceptions.IllegalEarthProbabilityException;
import backend.academy.maze.exceptions.IllegalSizeValueException;
import backend.academy.maze.generators.Generator;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.surface.RandomSurfaceGenerator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import static backend.academy.maze.utils.MazeUtils.makeGraphImperfect;

@RequiredArgsConstructor
public class BacktrackingMethod implements Generator {
    private static final double MAX_EARTH_PROBABILITY = 1;

    private final Random random;
    private final RandomSurfaceGenerator surfaceGenerator;

    /**
     * Метод Рекурсивного бэк трекера
     * Алгоритм:
     * 1. Мы выбираем вершину, добавляем ее в стэк вершин
     * 2. Берем рандомную соседнюю непосещенную вершину, соединяем вершины ребром с весом из рандомайзера весов (),
     * 3. С новой вершиной проделываем шаги 1 и 2, до тех пор пока нам встречаются вершины с непосещенными соседями,
     * Иначе шаг 4
     * 4. Достаем вершину из стека и проделываем шаги 2-3
     * 5. Алгоритм заканчивает работу, когда стэк становится пустым
     *
     * @param height           - высота лабиринта
     * @param width            - ширина лабиринта
     * @param earthProbability - вероятность пустого прохода
     * @return граф сгенерированного лабиринта
     */
    @Override
    public GraphMaze generate(int height, int width, double earthProbability, boolean perfectFlag) {
        checkAttributes(height, width, earthProbability);
        GraphMaze graphMaze = new GraphMaze(height, width);
        Set<Coordinate> visitedCoordinates = new HashSet<>();
        Deque<Coordinate> stack = new ArrayDeque<>();

        Coordinate currCoordinate = new Coordinate(0, 0);
        markCoordinateAsVisited(currCoordinate, visitedCoordinates);
        stack.add(currCoordinate);

        while (!stack.isEmpty()) {
            List<Coordinate> unvisitedCoordinates =
                findAdjacentUnvisitedCoordinates(graphMaze, currCoordinate, visitedCoordinates);
            if (!unvisitedCoordinates.isEmpty()) {
                Coordinate neighbour = getRandomCoordinate(unvisitedCoordinates);
                graphMaze.addEdge(currCoordinate, neighbour, surfaceGenerator.getSurface(earthProbability));
                stack.push(neighbour);
                currCoordinate = neighbour;
                markCoordinateAsVisited(currCoordinate, visitedCoordinates);
            } else {
                currCoordinate = stack.pop();
            }
        }
        if (!perfectFlag) {
            makeGraphImperfect(graphMaze, random.nextInt(width / 2) + 1, surfaceGenerator, earthProbability);
        }
        return graphMaze;
    }

    private void checkAttributes(int height, int width, double earthProbability) {
        if (height <= 0 || width <= 0) {
            throw new IllegalSizeValueException();
        }
        if (earthProbability <= 0 || earthProbability > MAX_EARTH_PROBABILITY) {
            throw new IllegalEarthProbabilityException();
        }
    }

    private List<Coordinate> findAdjacentUnvisitedCoordinates(
        GraphMaze graphMaze,
        Coordinate currCoordinate,
        Set<Coordinate> visitedCoordinates
    ) {
        List<Coordinate> adjacentCoordinates = new ArrayList<>();

        Coordinate[] directions = {
            new Coordinate(0, 1),   // Вверх
            new Coordinate(1, 0),   // Вправо
            new Coordinate(0, -1),  // Вниз
            new Coordinate(-1, 0)   // Влево
        };

        for (Coordinate direction : directions) {
            Coordinate newCoordinate = currCoordinate.add(direction);

            if (isWithinBounds(newCoordinate, graphMaze.height(), graphMaze.width())
                && isUnvisitedCoordinate(newCoordinate, visitedCoordinates)) {
                adjacentCoordinates.add(newCoordinate);
            }
        }

        return adjacentCoordinates;
    }

    private boolean isUnvisitedCoordinate(Coordinate coordinate, Set<Coordinate> visitedCoordinates) {
        return !visitedCoordinates.contains(coordinate);
    }

    private boolean isWithinBounds(Coordinate coordinate, int height, int width) {
        return coordinate.col() >= 0 && coordinate.col() < width && coordinate.row() >= 0 && coordinate.row() < height;
    }

    private Coordinate getRandomCoordinate(List<Coordinate> unvisitedCoordinates) {
        int randomIndex = random.nextInt(unvisitedCoordinates.size());

        return unvisitedCoordinates.get(randomIndex);
    }

    private void markCoordinateAsVisited(Coordinate coordinate, Set<Coordinate> visitedCoordinates) {
        visitedCoordinates.add(coordinate);
    }

}
