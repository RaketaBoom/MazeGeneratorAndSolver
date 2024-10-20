package backend.academy.maze.renderers.impl;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Edge;
import backend.academy.maze.models.GraphMaze;
import backend.academy.maze.models.Vertex;
import backend.academy.maze.renderers.Renderer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlusMinusRenderer implements Renderer {
    private static final char PATH_SYMBOL = '@';

    @Override
    public String render(GraphMaze graphMaze) {
        List<StringBuilder> lines = createLinesOfGraph(graphMaze);

        return getStringFromLines(lines);
    }

    private static String getStringFromLines(List<StringBuilder> lines) {
        return lines.stream()
            .map(StringBuilder::toString)
            .collect(Collectors.joining("\n"));
    }

    @Override
    public String render(GraphMaze graphMaze, List<Coordinate> path) {
        List<StringBuilder> lines = createLinesOfGraph(graphMaze);
        drawPath(lines, path);

        return getStringFromLines(lines);
    }

    private void drawPath(List<StringBuilder> lines, List<Coordinate> path) {
        for (Coordinate coord : path) {
            int col = 2 + 4 * coord.col();
            int row = 1 + 2 * coord.row();
            lines.get(row).setCharAt(col, PATH_SYMBOL);
        }
    }

    private List<StringBuilder> createLinesOfGraph(GraphMaze graphMaze) {
        int n = graphMaze.height();
        List<StringBuilder> lines = generateLines(n * 2 + 1);
        fillFirstStep(lines, graphMaze);
        fillSecondStep(lines, graphMaze);
        fillThirdStep(lines, graphMaze);
        fillFourthStep(lines, graphMaze);
        fillFifthStep(lines, graphMaze);

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
    private void fillFirstStep(List<StringBuilder> lines, GraphMaze graphMaze) {
        StringBuilder firstLine = lines.get(0);
        StringBuilder secondLine = lines.get(1);
        drawWallCorner(firstLine, secondLine);
        for (int j = 1; j < graphMaze.width(); j++) {
            Vertex fourthVertex = graphMaze.graph()[0][j];
            Vertex thirdVertex = graphMaze.graph()[0][j - 1];
            Optional<Edge> edge = graphMaze.findEdge(fourthVertex, thirdVertex);
            if (edge.isPresent()) {
                String surfaceSymbol = edge.get().surface().symbol();
                drawHorizontalWallWithPassage(firstLine, secondLine, surfaceSymbol);
            } else {
                drawWallCorner(firstLine, secondLine);
            }
        }
        drawVerticalWallWithConnection(firstLine, secondLine);
    }

    /**
     * Рендерится левая сторона лабиринта
     */
    private void fillSecondStep(List<StringBuilder> lines, GraphMaze graphMaze) {
        for (int i = 1; i < graphMaze.height(); i++) {
            StringBuilder firstLine = lines.get(2 * i);
            StringBuilder secondLine = lines.get(2 * i + 1);
            Vertex firstVertex = graphMaze.graph()[i - 1][0];
            Vertex fourthVertex = graphMaze.graph()[i][0];
            Optional<Edge> edge = graphMaze.findEdge(firstVertex, fourthVertex);
            if (edge.isPresent()) {
                String surfaceSymbol = edge.get().surface().symbol();
                drawVerticalWallWithPassage(firstLine, surfaceSymbol, secondLine);
            } else {
                drawWallCorner(firstLine, secondLine);
            }
        }
        StringBuilder lastLine = lines.get(graphMaze.height() * 2);
        drawEmptyHorizontalWallWithConnection(lastLine);
    }

    /**
     * Рендерниг нижней стороны лабиринта
     */
    private void fillThirdStep(List<StringBuilder> lines, GraphMaze graphMaze) {
        StringBuilder line = lines.get(graphMaze.height() * 2);
        for (int j = 1; j < graphMaze.width(); j++) {
            Vertex firstVertex = graphMaze.graph()[graphMaze.height() - 1][j];
            Vertex secondVertex = graphMaze.graph()[graphMaze.height() - 1][j - 1];
            Optional<Edge> edge = graphMaze.findEdge(firstVertex, secondVertex);
            if (edge.isPresent()) {
                drawEmptyHorizontalWall(line);
            } else {
                drawEmptyHorizontalWallWithConnection(line);
            }
        }
        drawEmptyCorner(line);
    }

    /**
     * Рендеринг основной внутренней части лабиринта
     */
    private void fillFourthStep(List<StringBuilder> lines, GraphMaze graphMaze) {
        for (int i = 1; i < graphMaze.height(); i++) {
            StringBuilder firstLine = lines.get(2 * i);
            StringBuilder secondLine = lines.get(2 * i + 1);
            for (int j = 1; j < graphMaze.width(); j++) {
                Vertex firstVertex = graphMaze.graph()[i - 1][j];
                Vertex secondVertex = graphMaze.graph()[i - 1][j - 1];
                Vertex thirdVertex = graphMaze.graph()[i][j - 1];
                Vertex fourthVertex = graphMaze.graph()[i][j];

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
    private void fillFifthStep(List<StringBuilder> lines, GraphMaze graphMaze) {
        for (int i = 1; i < graphMaze.height(); i++) {
            StringBuilder firstLine = lines.get(2 * i);
            StringBuilder secondLine = lines.get(2 * i + 1);
            Vertex secondVertex = graphMaze.graph()[i - 1][graphMaze.width() - 1];
            Vertex thirdVertex = graphMaze.graph()[i][graphMaze.width() - 1];
            Optional<Edge> edge = graphMaze.findEdge(secondVertex, thirdVertex);
            if (edge.isPresent()) {
                firstLine.append("|");
                secondLine.append("|");
            } else {
                drawVerticalWallWithConnection(firstLine, secondLine);
            }
        }
    }

    private static void drawBothPassages(
        StringBuilder firstLine,
        StringBuilder secondLine,
        Optional<Edge> edge4,
        Optional<Edge> edge3
    ) {
        firstLine.append("+ ");
        firstLine.append(edge4.get().surface().symbol());
        firstLine.append(" ");
        secondLine.append(edge3.get().surface().symbol());
        secondLine.append("   ");
    }

    private static void drawHorizontalWallWithConnection(
        StringBuilder firstLine,
        StringBuilder secondLine,
        Optional<Edge> edge3
    ) {
        firstLine.append("+---");
        secondLine.append("  ");
        secondLine.append(edge3.get().surface().symbol());
        secondLine.append(" ");
    }

    private static boolean isHorizontalWallWithConnection(Optional<Edge> edge3, Optional<Edge> edge4) {
        return edge3.isPresent()
            && edge4.isEmpty();
    }

    private static void drawVerticalWallWithConnection(
        StringBuilder firstLine,
        StringBuilder secondLine,
        Optional<Edge> edge4
    ) {
        firstLine.append("+ ");
        firstLine.append(edge4.get().surface().symbol());
        firstLine.append(" ");
        secondLine.append("|   ");
    }

    private static boolean isVerticalWallWithConnection(Optional<Edge> edge4, Optional<Edge> edge3) {
        return edge4.isPresent()
            && edge3.isEmpty();
    }

    private static boolean isWallCorner(Optional<Edge> edge4, Optional<Edge> edge3) {
        return edge4.isEmpty()
            && edge3.isEmpty();
    }

    private static void drawParallelVerticalPassage(
        StringBuilder firstLine,
        StringBuilder secondLine,
        Optional<Edge> edge4
    ) {
        firstLine.append("| ");
        firstLine.append(edge4.get().surface().symbol());
        firstLine.append(" ");
        secondLine.append("|   ");
    }

    private static void drawWallCorner(StringBuilder firstLine, StringBuilder secondLine) {
        firstLine.append("+---");
        secondLine.append("|   ");
    }

    private static void drawHorizontalWallWithPassage(
        StringBuilder firstLine,
        StringBuilder secondLine,
        String surfaceSymbol
    ) {
        firstLine.append("----");
        secondLine.append(surfaceSymbol);
        secondLine.append("   ");
    }

    private static void drawVerticalWallWithConnection(StringBuilder firstLine, StringBuilder secondLine) {
        firstLine.append("+");
        secondLine.append("|");
    }

    private static void drawEmptyHorizontalWallWithConnection(StringBuilder lastLine) {
        lastLine.append("+---");
    }

    private static void drawEmptyCorner(StringBuilder line) {
        line.append("+\n");
    }

    private static void drawEmptyHorizontalWall(StringBuilder line) {
        line.append("----");
    }

    private static void drawVerticalWallWithPassage(
        StringBuilder firstLine,
        String surfaceSymbol,
        StringBuilder secondLine
    ) {
        firstLine.append("| ");
        firstLine.append(surfaceSymbol);
        firstLine.append(" ");
        secondLine.append("|   ");
    }

    private static boolean isParallelVerticalPassage(
        Optional<Edge> edge1,
        Optional<Edge> edge2,
        Optional<Edge> edge3,
        Optional<Edge> edge4
    ) {
        return edge1.isEmpty()
            && edge3.isEmpty()
            && edge4.isPresent()
            && edge2.isPresent();
    }

    private static void drawParralelHorizontalPassage(
        StringBuilder firstLine,
        StringBuilder secondLine,
        Optional<Edge> edge3
    ) {
        firstLine.append("----");
        secondLine.append(edge3.get().surface().symbol());
        secondLine.append("   ");
    }

    private static boolean isParallelHorizontalPassage(
        Optional<Edge> edge1,
        Optional<Edge> edge2,
        Optional<Edge> edge3,
        Optional<Edge> edge4
    ) {
        return edge1.isPresent()
            && edge2.isEmpty()
            && edge3.isPresent()
            && edge4.isEmpty();
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
        Optional<Edge> edge1 = graphMaze.findEdge(firstVertex, secondVertex); // Ребро для связи 1 и 2 вершины
        Optional<Edge> edge2 = graphMaze.findEdge(secondVertex, thirdVertex); // Ребро для связи 2 и 3 вершины
        Optional<Edge> edge3 = graphMaze.findEdge(thirdVertex, fourthVertex); // Ребро для связи 3 и 4 вершины
        Optional<Edge> edge4 = graphMaze.findEdge(fourthVertex, firstVertex); // Ребро для связи 4 и 1 вершины

        if (isParallelHorizontalPassage(edge1, edge2, edge3, edge4)) {
            drawParralelHorizontalPassage(firstLine, secondLine, edge3);
        } else if (isParallelVerticalPassage(edge1, edge2, edge3, edge4)) {
            drawParallelVerticalPassage(firstLine, secondLine, edge4);
        } else if (isWallCorner(edge4, edge3)) {
            drawWallCorner(firstLine, secondLine);
        } else if (isVerticalWallWithConnection(edge4, edge3)) {
            drawVerticalWallWithConnection(firstLine, secondLine, edge4);

        } else if (
            isHorizontalWallWithConnection(edge3, edge4)
        ) {
            drawHorizontalWallWithConnection(firstLine, secondLine, edge3);
        } else {
            drawBothPassages(firstLine, secondLine, edge4, edge3);
        }
    }

}
