package backend.academy.maze.graph;

import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Vertex {
    private int row;
    private int col;
    private int skeletonNumber;
    private Edge right;
    private Edge left;
    private Edge up;
    private Edge dawn;

    public Vertex (int row, int col){
        this.row = row;
        this.col = col;
    }

    public Optional<Edge> findEdge(Vertex v) {
        if (right != null && right.contain(v)) {
            return Optional.of(right);
        }
        if (left != null && left.contain(v)) {
            return Optional.of(left);
        }
        if (up != null && up.contain(v)) {
            return Optional.of(up);
        }
        if (dawn != null && dawn.contain(v)) {
            return Optional.of(dawn);
        }
        return Optional.empty();
    }

}
