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
        Edge foundEdge = null;

        if (right != null && right.contain(v)) {
            foundEdge = right;
        } else if (left != null && left.contain(v)) {
            foundEdge = left;
        } else if (up != null && up.contain(v)) {
            foundEdge = up;
        } else if (dawn != null && dawn.contain(v)) {
            foundEdge = dawn;
        }

        return Optional.ofNullable(foundEdge);
    }

    public List<Edge> findAllEdges() {
        List<Edge> list = new ArrayList<>();
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vertex vertex = (Vertex) o;

        if (!Objects.equals(right, vertex.right)) {
            return false;
        }
        if (!Objects.equals(left, vertex.left)) {
            return false;
        }
        if (!Objects.equals(up, vertex.up)) {
            return false;
        }
        return Objects.equals(dawn, vertex.dawn);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 37 * result + (left != null ? left.hashCode() : 0);
        result = 41 * result + (up != null ? up.hashCode() : 0);
        result = 43 * result + (dawn != null ? dawn.hashCode() : 0);
        return result;
    }
}
