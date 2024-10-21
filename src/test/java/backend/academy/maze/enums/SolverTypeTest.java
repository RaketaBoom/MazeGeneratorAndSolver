package backend.academy.maze.enums;

import backend.academy.maze.exceptions.NonSolverNumberException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolverTypeTest {

    @Test
    void testValueOf_NumOfKruskalMethod_KRUSKAL_METHOD() {
        //Arrange
        int num = 0;
        SolverType expectedType = SolverType.DIJKSTRA;

        //Act
        SolverType actualType = SolverType.valueOf(num);

        //Assert
        assertEquals(expectedType, actualType);
    }

    @Test
    void testValueOf_OutOfBoundsNum_NonGeneratorNumberExceptionThrown() {
        //Arrange
        int num = -1;

        //Act & Assert
        assertThrows(NonSolverNumberException.class, () -> SolverType.valueOf(num));
    }
}
