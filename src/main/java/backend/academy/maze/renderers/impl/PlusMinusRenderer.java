package backend.academy.maze.renderers.impl;

import backend.academy.maze.enums.Surface;
import backend.academy.maze.exceptions.EdgeNotFoundException;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Vertex;
import backend.academy.maze.renderers.Renderer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

public class PlusMinusRenderer implements Renderer {
    private static final char PATH_SYMBOL = '@';

    @Override
    public String render(GraphMaze graphMaze) {
        List<StringBuilder> lines = createLinesOfGraph(graphMaze);
        makeGridNumbered(lines, graphMaze.height(), graphMaze.width());

        return getStringFromLines(lines);
    }

    private void makeGridNumbered(List<StringBuilder> lines, int height, int width) {
        StringBuilder line = lines.getFirst();
        line.append("     ");
        for (int i = 0; i < width - 1; i++) {
            line.append(String.format("%-4d", i));
        }
        line.append(width - 1);
        for (int i = 0; i < height + 1; i++) {
            lines.get(1 + 2 * i).insert(0, "   ");
        }
        for (int i = 0; i < height; i++) {
            lines.get(2 + 2 * i).insert(0, String.format("%-3d", i));
        }
    }

    @Override
    public String render(GraphMaze graphMaze, List<Coordinate> path) {
        List<StringBuilder> lines = createLinesOfGraph(graphMaze);
        drawPath(lines, path);
        makeGridNumbered(lines, graphMaze.height(), graphMaze.width());

        return getStringFromLines(lines);
    }

    private static String getStringFromLines(List<StringBuilder> lines) {
        return lines.stream()
            .map(StringBuilder::toString)
            .collect(Collectors.joining("\n"));
    }

    private void drawPath(List<StringBuilder> lines, List<Coordinate> path) {
        for (Coordinate coordinate : path) {
            int col = 2 + 4 * coordinate.col();
            int row = 1 + 2 * coordinate.row();
            lines.get(row).setCharAt(col, PATH_SYMBOL);
        }
    }

    private List<StringBuilder> createLinesOfGraph(GraphMaze graphMaze) {
        int n = graphMaze.height();
        List<StringBuilder> lines = generateLines(n * 2 + 2);
        drawNorthWalls(lines, graphMaze);
        drawWestWalls(lines, graphMaze);
        drawSouthWalls(lines, graphMaze);
        drawBodyMaze(lines, graphMaze);
        drawEastWalls(lines, graphMaze);

        return lines;
    }

    private List<StringBuilder> generateLines(int size) {
        return Stream.generate(StringBuilder::new)
            .limit(size)
            .toList();
    }

    /**
     * Ренедерится верхняя сторона лабиринта
     */
    private void drawNorthWalls(List<StringBuilder> lines, GraphMaze graphMaze) {
        StringBuilder firstLine = lines.get(1);
        StringBuilder secondLine = lines.get(2);
        drawWall(firstLine, secondLine, Wall.WALL_INTERSECTION);
        drawCell(secondLine);
        for (int j = 1; j < graphMaze.width(); j++) {
            Vertex firstVertex = graphMaze.getVertex(new Coordinate(0, j));
            Vertex secondVertex = graphMaze.getVertex(new Coordinate(0, j - 1));
            Optional<Edge> edge = graphMaze.findEdge(firstVertex, secondVertex);
            if (edge.isPresent()) {
                Surface surface = edge.get().surface();
                drawWall(firstLine, secondLine, Wall.HORIZONTAL_WALL);
                drawDawnSurface(secondLine, surface);
                drawCell(secondLine);
            } else {
                drawWall(firstLine, secondLine, Wall.WALL_INTERSECTION);
                drawCell(secondLine);
            }
        }
        drawWall(firstLine, secondLine, Wall.VERTICAL_WALL_START);
    }

    /**
     * Рендерится левая сторона лабиринта
     */
    private void drawWestWalls(List<StringBuilder> lines, GraphMaze graphMaze) {
        for (int i = 1; i < graphMaze.height(); i++) {
            StringBuilder firstLine = lines.get(2 * i + 1);
            StringBuilder secondLine = lines.get(2 * i + 2);
            Vertex firstVertex = graphMaze.getVertex(new Coordinate(i - 1, 0));
            Vertex secondVertex = graphMaze.getVertex(new Coordinate(i, 0));
            Optional<Edge> edge = graphMaze.findEdge(firstVertex, secondVertex);
            if (edge.isPresent()) {
                Surface surface = edge.get().surface();
                drawWall(firstLine, secondLine, Wall.VERTICAL_WALL);
                drawRightSurface(firstLine, surface);
                drawCell(secondLine);
            } else {
                drawWall(firstLine, secondLine, Wall.WALL_INTERSECTION);
                drawCell(secondLine);
            }
        }
        StringBuilder lastLine = lines.get(graphMaze.height() * 2 + 1);
        drawWall(lastLine, Wall.HORIZONTAL_WALL_START);
    }

    /**
     * Рендерниг нижней стороны лабиринта
     */
    private void drawSouthWalls(List<StringBuilder> lines, GraphMaze graphMaze) {
        StringBuilder line = lines.get(graphMaze.height() * 2 + 1);
        for (int j = 1; j < graphMaze.width(); j++) {
            Vertex firstVertex = graphMaze.getVertex(new Coordinate(graphMaze.height() - 1, j));
            Vertex secondVertex = graphMaze.getVertex(new Coordinate(graphMaze.height() - 1, j - 1));
            Optional<Edge> edge = graphMaze.findEdge(firstVertex, secondVertex);
            if (edge.isPresent()) {
                drawWall(line, Wall.HORIZONTAL_WALL);
            } else {
                drawWall(line, Wall.HORIZONTAL_WALL_START);
            }
        }
        drawWall(line, Wall.WALL_END);
    }

    /**
     * Рендеринг основной внутренней части лабиринта
     */
    private void drawBodyMaze(List<StringBuilder> lines, GraphMaze graphMaze) {
        for (int i = 1; i < graphMaze.height(); i++) {
            StringBuilder firstLine = lines.get(2 * i + 1);
            StringBuilder secondLine = lines.get(2 * i + 2);
            for (int j = 1; j < graphMaze.width(); j++) {
                Vertex firstVertex = graphMaze.getVertex(new Coordinate(i - 1, j));
                Vertex secondVertex = graphMaze.getVertex(new Coordinate(i - 1, j - 1));
                Vertex thirdVertex = graphMaze.getVertex(new Coordinate(i, j - 1));
                Vertex fourthVertex = graphMaze.getVertex(new Coordinate(i, j));

                drawBlock(
                    firstLine,
                    secondLine,
                    graphMaze,
                    firstVertex,
                    secondVertex,
                    thirdVertex,
                    fourthVertex
                );
            }
        }
    }

    /**
     * Рендеринг правой стороны лабиринта
     */
    private void drawEastWalls(List<StringBuilder> lines, GraphMaze graphMaze) {
        for (int i = 1; i < graphMaze.height(); i++) {
            StringBuilder firstLine = lines.get(2 * i + 1);
            StringBuilder secondLine = lines.get(2 * i + 2);
            Vertex firstVertex = graphMaze.getVertex(new Coordinate(i - 1, graphMaze.width() - 1));
            Vertex secondVertex = graphMaze.getVertex(new Coordinate(i, graphMaze.width() - 1));
            Optional<Edge> edge = graphMaze.findEdge(firstVertex, secondVertex);
            if (edge.isPresent()) {
                drawWall(firstLine, secondLine, Wall.VERTICAL_WALL);
            } else {
                drawWall(firstLine, secondLine, Wall.VERTICAL_WALL_START);
            }
        }
    }

    private void drawBlock(
        StringBuilder firstLine,
        StringBuilder secondLine,
        GraphMaze graphMaze,
        Vertex firstVertex,
        Vertex secondVertex,
        Vertex thirdVertex,
        Vertex fourthVertex
    ) {
        Optional<Edge> edgeOptional1 = graphMaze.findEdge(firstVertex, secondVertex); // Ребро для связи 1 и 2 вершины
        Optional<Edge> edgeOptional2 = graphMaze.findEdge(secondVertex, thirdVertex); // Ребро для связи 2 и 3 вершины
        Optional<Edge> edgeOptional3 = graphMaze.findEdge(thirdVertex, fourthVertex); // Ребро для связи 3 и 4 вершины
        Optional<Edge> edgeOptional4 = graphMaze.findEdge(fourthVertex, firstVertex); // Ребро для связи 4 и 1 вершины

        Wall wall = wallType(
            edgeOptional1.isPresent(),
            edgeOptional2.isPresent(),
            edgeOptional3.isPresent(),
            edgeOptional4.isPresent()
        );

        switch (wall) {
            case WALL_END -> {
                Edge edgeRight = edgeOptional4.orElseThrow(EdgeNotFoundException::new);
                Edge edgeDawn = edgeOptional3.orElseThrow(EdgeNotFoundException::new);
                drawWall(firstLine, wall);
                drawRightSurface(firstLine, edgeRight.surface());
                drawDawnSurface(secondLine, edgeDawn.surface());
            }
            case WALL_INTERSECTION -> drawWall(firstLine, secondLine, wall);
            case HORIZONTAL_WALL, HORIZONTAL_WALL_START -> {
                Edge edgeDawn = edgeOptional3.orElseThrow(EdgeNotFoundException::new);
                drawWall(firstLine, wall);
                drawDawnSurface(secondLine, edgeDawn.surface());
            }
            case VERTICAL_WALL, VERTICAL_WALL_START -> {
                Edge edgeRight = edgeOptional4.orElseThrow(EdgeNotFoundException::new);
                drawWall(firstLine, secondLine, wall);
                drawRightSurface(firstLine, edgeRight.surface());
            }
        }
        drawCell(secondLine);
    }

    private Wall wallType(boolean presentEdge1, boolean presentEdge2, boolean presentEdge3, boolean presentEdge4) {
        if (presentEdge3 && presentEdge4) {
            return Wall.WALL_END;
        }
        if (!presentEdge3 && !presentEdge4) {
            return Wall.WALL_INTERSECTION;
        }
        // presentEdge3 != presentEdge4
        if (presentEdge1 && !presentEdge2 && presentEdge3) {
            return Wall.HORIZONTAL_WALL;
        }
        if (!presentEdge1 && presentEdge2 && !presentEdge3) {
            return Wall.VERTICAL_WALL;
        }

        if (!presentEdge3) {
            return Wall.VERTICAL_WALL_START;
        }
        return Wall.HORIZONTAL_WALL_START;
    }

    private void drawWall(StringBuilder firstLine, StringBuilder secondLine, Wall wall) {
        firstLine.append(wall.firstLine());
        secondLine.append(wall.secondLine());
    }

    private void drawWall(StringBuilder line, Wall wall) {
        line.append(wall.firstLine());
    }

    private void drawRightSurface(StringBuilder firstLine, Surface surface) {
        firstLine.append(STR." \{surface.symbol()} ");
    }

    private void drawDawnSurface(StringBuilder secondLine, Surface surface) {
        secondLine.append(surface.symbol());
    }

    private void drawCell(StringBuilder secondLine) {
        secondLine.append("   ");
    }

    @Getter
    enum Wall {
        WALL_END("+", ""),
        VERTICAL_WALL_START("+", "|"),
        HORIZONTAL_WALL_START("+---", ""),
        WALL_INTERSECTION("+---", "|"),
        HORIZONTAL_WALL("----", ""),
        VERTICAL_WALL("|", "|");
        private final String firstLine;
        private final String secondLine;

        Wall(String s1, String s2) {
            this.firstLine = s1;
            this.secondLine = s2;
        }
    }
}
