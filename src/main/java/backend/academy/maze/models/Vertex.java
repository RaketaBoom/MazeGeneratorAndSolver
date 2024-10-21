package backend.academy.maze.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public List<Edge> findAllEdges() {
        List<Edge> list = new ArrayList<>(4);
        if (right != null) {
            list.add(right);
        }
        if (left != null) {
            list.add(left);
        }
        if (up != null) {
            list.add(up);
        }
        if (dawn != null) {
            list.add(dawn);
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (!Objects.equals(right, vertex.right)) return false;
        if (!Objects.equals(left, vertex.left)) return false;
        if (!Objects.equals(up, vertex.up)) return false;
        return Objects.equals(dawn, vertex.dawn);
    }

    @Override
    public int hashCode() {
        int result = right != null ? right.hashCode() : 0;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (up != null ? up.hashCode() : 0);
        result = 31 * result + (dawn != null ? dawn.hashCode() : 0);
        return result;
    }
}
