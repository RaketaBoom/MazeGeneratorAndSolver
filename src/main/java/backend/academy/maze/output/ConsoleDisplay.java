package backend.academy.maze.output;

import java.io.PrintStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsoleDisplay {
    private final PrintStream stream;

    public void choiceGeneratorType() {
        stream.printf("%nВыберите один из способов генерации лабиринта%n");
        stream.printf("1. Бэктрекер%n");
        stream.printf("2. Метод Краскалла%n");
        stream.printf("Укажите цифру: %n");
    }

    public void sizeOfMaze() {
        stream.printf("Введите, пожалуйства размеры лабиринта (высота и ширина):%n");
    }

    public void choicePerfectFlag() {
        stream.printf("Идеальный лабиринт (да/нет):%n");
    }

    public void maze(String stringMaze) {
        stream.printf("Построенный лабиринт:%n");
        stream.printf(stringMaze);
    }

    public void enterStartPoint() {
        stream.printf("%nВведите координаты начальной точки:%n");
    }

    public void enterEndPoint() {
        stream.printf("Введите координаты конечной точки:%n");
    }

    public void choiceSolver() {
        stream.printf("%nВыберите один из способов алгоритмов решения лабиринта%n");
        stream.printf("1. Алгоритм Дейкстры%n");
        stream.printf("2. Алгоритм А*%n");
        stream.printf("Выберите цифру: %n");
    }

    public void solution(String stringMaze, int distance) {
        stream.printf("Построенный маршрут:%n");
        stream.printf(stringMaze);
        stream.printf("%nЗатраченное время: %d минут%n", distance);
    }

    public void errorMessage(String message) {
        stream.printf("%s%n", message);
    }
}
