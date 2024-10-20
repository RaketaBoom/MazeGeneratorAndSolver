package backend.academy.maze.models;

public record Coordinate(int row, int col) {
    public Coordinate add(Coordinate other) {
        return new Coordinate(this.row + other.row, this.col + other.col);
    }
}
