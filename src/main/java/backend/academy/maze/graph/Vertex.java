package backend.academy.maze.graph;

import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Vertex {
    private Edge right;
    private Edge left;
    private Edge up;
    private Edge dawn;

    public Optional<Edge> findEdge(Vertex v) {
        if (right != null && (right.v1() == v || right.v2() == v)) {
            return Optional.of(right);
        }
        if (left != null && (left.v1() == v || left.v2() == v)) {
            return Optional.of(left);
        }
        if (up != null && (up.v1() == v || up.v2() == v)) {
            return Optional.of(up);
        }
        if (dawn != null && (dawn.v1() == v || dawn.v2() == v)) {
            return Optional.of(dawn);
        }
        return Optional.empty();
    }

}
