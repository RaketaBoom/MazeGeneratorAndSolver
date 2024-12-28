package backend.academy.maze.solvers.impl;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Solution;
import backend.academy.maze.solvers.Solver;
import java.util.Comparator;
import java.util.HashMap;

public class DijkstraAlgorithm extends SolverAlgorithm implements Solver {

    /**
     * Метод Дейкстры
     * Алгоритм:
     * 1. Вначале указываем, что для всех вершин расстояние до начальной точки равно бесконечности, в приоритетную
     * очередь добавляем стартовую вершину
     * 2. Достаем из очереди вершину
     * 3. Для для текущей вершины высчитываем расстояние до соседних вершин (расстояние до текущей + расстояние
     * между вершинами)
     * 4. Если новое рассчитанное расстояние меньше, чем указано в мапе distances, то обновляем значение расстояния
     * по ключу, заносим в приоритетную очередь текущую вершину, записываем мапу previous ключ-следующая вершина,
     * значение-текущая
     * 5. Повторяем шаги 2-4, пока текущей вершиной не станет финишная (восстанавливаем путь и возвращаем вместе
     * с расстоянием) или пока очередь не закончится (значит решения нет, возвращаем пустой список и расстояние 0)
     */
    @Override
    public Solution solve(GraphMaze maze, Coordinate start, Coordinate end) {
        distances = new HashMap<>();
        Comparator<Coordinate> comparator = Comparator.comparingInt(distances::get);

        return launch(maze, start, end, comparator);
    }

}
