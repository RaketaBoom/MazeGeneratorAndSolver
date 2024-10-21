package backend.academy.maze.enums;

import backend.academy.maze.exceptions.NonGeneratorNumberException;

/**
 * Содержит типы генерации лабиринта
 */
public enum GeneratorType {
    BACKTRACKING_METHOD,
    KRUSKAL_METHOD;

    /**
     * Возвращает объект соответствующий номеру
     * @param num - номер типа генератора
     * @return тип генератора
     * @throws NonGeneratorNumberException если номер не соответствует типу генератора
     */
    public static GeneratorType valueOf(int num) {
        if (num < 1 || num > GeneratorType.values().length) {
            throw new NonGeneratorNumberException();
        }
        return GeneratorType.values()[num - 1];
    }
}
