package backend.academy.maze.enums;

import backend.academy.maze.exceptions.NonGeneratorNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTypeTest {

    @Test
    void testValueOf_NumOfKruskalMethod_KRUSKAL_METHOD() {
        //Arrange
        int num = 1;
        GeneratorType expectedType = GeneratorType.KRUSKAL_METHOD;

        //Act
        GeneratorType actualType = GeneratorType.valueOf(num);

        //Assert
        assertEquals(expectedType, actualType);
    }

    @Test
    void testValueOf_OutOfBoundsNum_NonGeneratorNumberExceptionThrown() {
        //Arrange
        int num = -1;

        //Act & Assert
        assertThrows(NonGeneratorNumberException.class, () -> GeneratorType.valueOf(num));
    }
}
