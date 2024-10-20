package backend.academy.maze.generators.impl;

import backend.academy.maze.enums.Surface;
import backend.academy.maze.exceptions.IllegalEarthProbabilityException;
import backend.academy.maze.exceptions.IllegalSizeValueException;
import backend.academy.maze.generators.Generator;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.surface.RandomSurfaceGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;

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
     * @param height           - высота лабиринта
     * @param width            - ширина лабиринта
     * @param earthProbability - вероятность пустого прохода
     */
    @Override
    public GraphMaze generate(int height, int width, double earthProbability, boolean perfectFlag) {
        checkAttributes(height, width, earthProbability);
        numberSkeleton = 1;
        Map<Coordinate, Integer> coordinateSkeletonMap = new HashMap<>();
        GraphMaze graphMaze = new GraphMaze(height, width);
        RandomSurfaceGenerator surfaceGenerator = new RandomSurfaceGenerator(random);
        List<Pair> pairsAdjacentCoordinates = getPairsAdjacentCoordinates(height, width);

        while (!pairsAdjacentCoordinates.isEmpty()) {
            Pair pair = chooseRandomPairCoordinates(pairsAdjacentCoordinates);
            if (isDifferentSkeletons(pair, coordinateSkeletonMap)) {
                createEdge(graphMaze, pair, surfaceGenerator.getSurface(earthProbability));
                markVertexBelongingToOneSkeleton(pair, coordinateSkeletonMap);
            }
        }
        return graphMaze;
    }

    private List<Pair> getPairsAdjacentCoordinates(int height, int width) {
        List<Pair> list = new ArrayList<>(); // Надо записать емкость
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width - 1; j++) {
                list.add(new Pair(new Coordinate(i, j), new Coordinate(i, j + 1)));
            }
        }
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height - 1; i++) {
                list.add(new Pair(new Coordinate(i, j), new Coordinate(i + 1, j)));
            }
        }
        return list;
    }

    private Pair chooseRandomPairCoordinates(List<Pair> list) {
        int index = random.nextInt(list.size());
        return list.remove(index);
    }

    private boolean isDifferentSkeletons(Pair pair, Map<Coordinate, Integer> coordinateSkeletonMap) {
        if (!coordinateSkeletonMap.containsKey(pair.first()) || !coordinateSkeletonMap.containsKey(pair.second())) {
            return true;
        }
        return !coordinateSkeletonMap.get(pair.first()).equals(coordinateSkeletonMap.get(pair.second()));
    }

    private void createEdge(GraphMaze graphMaze, Pair pair, Surface surface) {
        graphMaze.addEdge(pair.first(), pair.second(), surface);
    }

    private void markVertexBelongingToOneSkeleton(Pair pair, Map<Coordinate, Integer> coordinateSkeletonMap) {
        Coordinate first = pair.first();
        Coordinate second = pair.second();
        Optional<Integer> firstSkeleton = Optional.ofNullable(coordinateSkeletonMap.get(first));
        Optional<Integer> secondSkeleton = Optional.ofNullable(coordinateSkeletonMap.get(second));
        if (firstSkeleton.isEmpty()
            && secondSkeleton.isEmpty()) {
            coordinateSkeletonMap.put(first, numberSkeleton);
            coordinateSkeletonMap.put(second, numberSkeleton);
            numberSkeleton += 1;
            return;
        }
        if (firstSkeleton.isEmpty()) { // DRY???
            coordinateSkeletonMap.put(first, secondSkeleton.get());
            return;
        }
        if (secondSkeleton.isEmpty()) {
            coordinateSkeletonMap.put(second, firstSkeleton.get());
            return;
        }
        if (firstSkeleton.get() < secondSkeleton.get()) {
            coordinateSkeletonMap
                .keySet()
                .stream()
                .filter(x -> coordinateSkeletonMap.get(x).equals(secondSkeleton.get()))
                .forEach(x -> coordinateSkeletonMap.put(x, firstSkeleton.get()));
            return;
        }
        if (firstSkeleton.get() > secondSkeleton.get()) {
            coordinateSkeletonMap
                .keySet()
                .stream()
                .filter(x -> coordinateSkeletonMap.get(x).equals(firstSkeleton.get()))
                .forEach(x -> coordinateSkeletonMap.put(x, secondSkeleton.get()));
        }
    }

    private void checkAttributes(int height, int width, double earthProbability) {
        if(height <= 0 || width <= 0){
            throw new IllegalSizeValueException();
        }
        if(earthProbability <= 0 || earthProbability > MAX_EARTH_PROBABILITY){
            throw new IllegalEarthProbabilityException();
        }
    }

    record Pair(
        Coordinate first,
        Coordinate second
    ) {
    }
}

