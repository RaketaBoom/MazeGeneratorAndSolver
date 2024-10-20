package backend.academy.maze.enums;

import backend.academy.maze.exceptions.NonSolverNumberException;

public enum SolverType {
    DIJKSTRA;

    public static SolverType valueOf(int num) {
        if (num < 1 || num > SolverType.values().length) {
            throw new NonSolverNumberException();
        }
        return SolverType.values()[num - 1];
    }
}
