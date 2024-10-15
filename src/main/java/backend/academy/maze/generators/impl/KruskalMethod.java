package backend.academy.maze.generators.impl;

import backend.academy.maze.generators.Generator;
import backend.academy.maze.graph.Coordinate;
import backend.academy.maze.graph.GraphMaze;
import backend.academy.maze.graph.Vertex;
import backend.academy.maze.surface.RandomSurfaceGenerator;
import backend.academy.maze.surface.Surface;
import lombok.RequiredArgsConstructor;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class KruskalMethod implements Generator {
    private final Random random;
    private int numberSkeleton;

    /**
     * Метод Краскала
     * Алгоритм:
     * 1. Рандомно берем пару соседних вершин
     * 2. Если обе вершины принадлежат разным остовам,
     * то соединяем их ребром и помечаем полученный остов одним номером (Наименьшим номером остова)
     * 3. Соединяем до тех пор, пока не переберем все соседние вершины
     *
     * @param height - высота лабиринта
     * @param width - ширина лабиринта
     * @param earthProbability - вероятность пустого прохода
     * @return
     */
    @Override
    public GraphMaze generate(int height, int width, double earthProbability) {
        numberSkeleton = 1;
        GraphMaze graphMaze = new GraphMaze(height, width);
        RandomSurfaceGenerator surfaceGenerator = new RandomSurfaceGenerator(random);
        List<Pair> pairsAdjacentCoordinates = getPairsAdjacentCoordinates(height, width);

        while(!pairsAdjacentCoordinates.isEmpty()){
            Pair pair = chooseRandomPairCoordinates(pairsAdjacentCoordinates);
            if (isDifferentSkeletons(graphMaze, pair)){
                createEdge(graphMaze, pair, surfaceGenerator.getSurface(earthProbability));
                markVertexBelongingToOneSkeleton(graphMaze, pair);
            }
        }
        return graphMaze;
    }

    private List<Pair> getPairsAdjacentCoordinates(int height, int width){
        List<Pair> list = new ArrayList<>(); // Надо записать емкость
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width - 1; j++) {
                list.add(new Pair(new Coordinate(i, j), new Coordinate(i, j+1)));
            }
        }
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height - 1; i++) {
                list.add(new Pair(new Coordinate(i, j), new Coordinate(i+1, j)));
            }
        }
        return list;
    }

    private Pair chooseRandomPairCoordinates(List<Pair> list){
        int index = random.nextInt(list.size());
        return list.remove(index);
    }

    private boolean isDifferentSkeletons(GraphMaze graphMaze, Pair pair) {
        Vertex first = graphMaze.getVertex(pair.first());
        Vertex second = graphMaze.getVertex(pair.second());
        return first.skeletonNumber() != second.skeletonNumber()
            || first.skeletonNumber() == 0;
    }

    private void createEdge(GraphMaze graphMaze, Pair pair, Surface surface){
        graphMaze.addEdge(pair.first(), pair.second(), surface);
    }

    private void markVertexBelongingToOneSkeleton(GraphMaze graphMaze, Pair pair){
        Vertex first = graphMaze.getVertex(pair.first());
        Vertex second = graphMaze.getVertex(pair.second());
        if (first.skeletonNumber() == 0
        && second.skeletonNumber() == 0){
            first.skeletonNumber(numberSkeleton);
            second.skeletonNumber(numberSkeleton);
            numberSkeleton += 1;
            return;
        }
        if (first.skeletonNumber() == 0){ // DRY???
            first.skeletonNumber(second.skeletonNumber());
            return;
        }
        if (second.skeletonNumber() == 0){
           second.skeletonNumber(first.skeletonNumber());
            return;
        }
        if (first.skeletonNumber() < second.skeletonNumber()){
            graphMaze.findSkeletonOfVertex(second)
                .forEach(x -> x.skeletonNumber(first.skeletonNumber()));
            return;
        }
        if (first.skeletonNumber() > second.skeletonNumber()){
            graphMaze.findSkeletonOfVertex(first)
                .forEach(x -> x.skeletonNumber(second.skeletonNumber()));
        }
    }
}

record Pair(
    Coordinate first,
    Coordinate second
){}
