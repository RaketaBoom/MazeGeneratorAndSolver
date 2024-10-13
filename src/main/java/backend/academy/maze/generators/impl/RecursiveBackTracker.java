package backend.academy.maze.generators.impl;

import backend.academy.maze.generators.Generator;
import backend.academy.maze.graph.Coordinate;
import backend.academy.maze.graph.GraphMaze;
import backend.academy.maze.graph.Vertex;
import backend.academy.maze.surface.RandomSurfaceGenerator;
import lombok.RequiredArgsConstructor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.Stack;

@RequiredArgsConstructor
public class RecursiveBackTracker implements Generator {
    public final Random random;


    /**
     * Метод Рекурсивного бэк трекера
     * Алгоритм:
     * 1. Мы выбираем вершину, добавляем ее в стэк вершин
     * 2. Берем рандомную непосещенную вершину (Отслеживаем как не принадлежность ни одному остову,
     * т.е. skeletonNumber = 0), соединяем вершины ребром с весом из рандомайзера весов (),
     * 3. С новой вершиной проделываем шаги 1 и 2, до тех пор пока нам встречаются вершины с непосещенными соседями,
     * Иначе шаг 4
     * 4. Достаем вершину из стека и проделываем шаги 2-3
     * 5. Алгоритм заканчивает работу, когда стэк становится пустым
     * @param height - высота лабиринта
     * @param width - ширина лабиринта
     * @return граф сгенерированного лабиринта
     */
    @Override
    public GraphMaze generate(int height, int width, double earthProbability) {
        GraphMaze graphMaze = new GraphMaze(height, width);
        RandomSurfaceGenerator surfaceGenerator = new RandomSurfaceGenerator(random);
        Deque<Coordinate> stack = new ArrayDeque<>();


        Coordinate currCoordinate = new Coordinate(0, 0);
        markVertexAsVisited(graphMaze.getVertex(currCoordinate));
        stack.add(currCoordinate);

        while(!stack.isEmpty()){
            List<Coordinate> unvisitedCoordinates = graphMaze.findAdjacentUnvisitedCoordinates(currCoordinate);
            if (!unvisitedCoordinates.isEmpty()){
                Coordinate neighbour = getRandomCoordinate(unvisitedCoordinates);
                graphMaze.addEdge(currCoordinate, neighbour, surfaceGenerator.getSurface(earthProbability));
                stack.push(neighbour);
                currCoordinate = neighbour;
                markVertexAsVisited(graphMaze.getVertex(currCoordinate));
            }else
            {
                currCoordinate = stack.pop();
            }
        }
        return graphMaze;
    }

    private Coordinate getRandomCoordinate(List<Coordinate> unvisitedCoordinates) {
        if (unvisitedCoordinates.isEmpty()) {
            throw new IllegalArgumentException("Список непосещенных координат пуст.");
        }

        int randomIndex = random.nextInt(unvisitedCoordinates.size());

        return unvisitedCoordinates.get(randomIndex);
    }

    void markVertexAsVisited(Vertex vertex){
        vertex.skeletonNumber(1);
    }

}
