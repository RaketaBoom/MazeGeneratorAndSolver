package backend.academy.maze.models;

/**
 * Рекорд для хранения координаты
 *
 * @param row - строка
 * @param col - столбец
 */
public record Coordinate(int row, int col) {
    /**
     * Сложение координат
     *
     * @param other - второе слагаемое
     * @return координата, полученная в результате сложения
     */
    public Coordinate add(Coordinate other) {
        return new Coordinate(this.row + other.row, this.col + other.col);
    }
}
