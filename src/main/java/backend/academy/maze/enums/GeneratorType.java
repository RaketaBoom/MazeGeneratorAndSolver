package backend.academy.maze.enums;

import backend.academy.maze.exceptions.NonGeneratorNumberException;

public enum GeneratorType {
    BACKTRACKING_METHOD,
    KRUSKAL_METHOD;

    public static GeneratorType valueOf(int num) {
        if (num < 1 || num > GeneratorType.values().length) {
            throw new NonGeneratorNumberException();
        }
        return GeneratorType.values()[num - 1];
    }
}
