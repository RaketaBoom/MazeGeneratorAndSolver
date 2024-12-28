package backend.academy.maze.enums;

import backend.academy.maze.exceptions.NonSolverNumberException;

/**
 * Содержит типы солверов (способов решений лабиринта)
 */
public enum SolverType {
    DIJKSTRA,
    A_STAR;

    /**
     * Возвращает объект соответствующий номеру
     *
     * @param num - номер типа солвера
     * @return тип солвера
     * @throws NonSolverNumberException если номер не соответствует типу солвера
     */
    public static SolverType valueOf(int num) {
        if (num < 1 || num > SolverType.values().length) {
            throw new NonSolverNumberException();
        }
        return SolverType.values()[num - 1];
    }
}
