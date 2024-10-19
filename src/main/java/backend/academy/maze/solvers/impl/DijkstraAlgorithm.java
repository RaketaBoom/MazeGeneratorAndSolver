package backend.academy.maze.solvers.impl;

import backend.academy.maze.graph.Coordinate;
import backend.academy.maze.graph.Edge;
import backend.academy.maze.graph.GraphMaze;
import backend.academy.maze.graph.Vertex;
import backend.academy.maze.solvers.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraAlgorithm implements Solver {

    @Override
    public List<Coordinate> solve(GraphMaze maze, Coordinate start, Coordinate end) {

        Map<Coordinate, Integer> distances = new HashMap<>();

        Map<Coordinate, Coordinate> previous = new HashMap<>();

        PriorityQueue<Coordinate> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

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
                return reconstructPath(previous, start, end);
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

        // Если не удалось найти путь, возвращаем пустой список
        return Collections.emptyList();
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> previous, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        for (Coordinate at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        if (!path.get(0).equals(start)) {
            return Collections.emptyList();
        }
        return path;
    }
}