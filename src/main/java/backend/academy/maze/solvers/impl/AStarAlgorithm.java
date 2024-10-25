package backend.academy.maze.solvers.impl;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Solution;
import backend.academy.maze.models.Vertex;
import backend.academy.maze.solvers.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AStarAlgorithm implements Solver {

    /**
     * А* алгоритм
     * По сути, взял лучшее от Дейкстры и от жадного алгоритма тем, что мы по-другому выбираем следующую вершину,
     * т.е. по-иному заполняем приоритетную очередь. При записи в приоритетную очередь, компаратор должен учитывать
     * расстояние до начальной точки и Манхетенское расстояние до финишной.
     */
    @Override
    public Solution solve(GraphMaze maze, Coordinate start, Coordinate end) {

        Map<Coordinate, Integer> distances = new HashMap<>();



        Map<Coordinate, Coordinate> previous = new HashMap<>();

        PriorityQueue<Coordinate> queue = new PriorityQueue<>(
            (c1, c2) -> Integer.compare(
                distances.get(c1)+manhattanDistance(c1, end),
                distances.get(c2)+manhattanDistance(c2, end)
            ));

        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                Coordinate c = new Coordinate(i, j);
                distances.put(c, Integer.MAX_VALUE);
            }
        }

        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(end)) {
                return new Solution(reconstructPath(previous, start, end), distances.get(end));
            }

            Vertex currentVertex = maze.getVertex(current);

            for (Edge edge : currentVertex.findAllEdges()) {
                Vertex neighbor = edge.v1().equals(currentVertex) ? edge.v2() : edge.v1();
                Coordinate neighborCoord = maze.getCoordinateOfVertex(neighbor);

                int newDist = distances.get(current) + edge.surface().value();

                if (newDist < distances.get(neighborCoord)) {
                    distances.put(neighborCoord, newDist);
                    previous.put(neighborCoord, current);

                    queue.add(neighborCoord);
                }
            }
        }

        return new Solution(Collections.emptyList(), 0);
    }

    private int manhattanDistance(Coordinate c1, Coordinate c2){
        return Math.abs(c1.col() - c2.col()) + Math.abs(c1.row() - c2.row());
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> previous, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        for (Coordinate at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        if (!path.getFirst().equals(start)) {
            return Collections.emptyList();
        }
        return path;
    }
}