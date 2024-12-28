package backend.academy.maze.input;

import backend.academy.maze.enums.GeneratorType;
import backend.academy.maze.enums.SolverType;
import backend.academy.maze.exceptions.IllegalCoordinateValueException;
import backend.academy.maze.exceptions.IllegalSizeValueException;
import backend.academy.maze.exceptions.NonGeneratorNumberException;
import backend.academy.maze.exceptions.UnformattedInputException;
import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Size;
import backend.academy.maze.validators.InputValidator;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class InputHandlerTest {
    InputHandler inputHandler;
    InputValidator inputValidator = new InputValidator();
    @Mock
    Scanner mockScanner;

    @ParameterizedTest
    @ValueSource(strings = {
        "4",
        "9",
        "k",
        "-",
        "0"
    })
    void testGetGeneratorType_IllegalSymbol_NonGeneratorNumberExceptionThrown(String line) {
        //Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn(line);
        inputHandler = new InputHandler(mockScanner, inputValidator);

        //Act && Assert
        assertThrows(NonGeneratorNumberException.class, () -> inputHandler.getGeneratorType());
    }

    @Test
    void testGetGeneratorType_RightEnter_GeneratorType() {
        //Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn("1");
        inputHandler = new InputHandler(mockScanner, inputValidator);

        //Act
        GeneratorType actualType = inputHandler.getGeneratorType();

        //Assert
        assertEquals(GeneratorType.BACKTRACKING_METHOD, actualType);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "4",
        "9 3 3",
        "k322",
        "-3",
        "0 2l"
    })
    void testGetSize_UnformattedInput_UnformattedInputExceptionThrown(String line) {
        //Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn(line);
        inputHandler = new InputHandler(mockScanner, inputValidator);

        //Act && Assert
        assertThrows(UnformattedInputException.class, () -> inputHandler.getSize());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "3 0",
        "0 3",
        "40 20"
    })
    void testGetSize_IllegalSizeValue_IllegalSizeValueExceptionThrown(String line) {
        //Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn(line);
        inputHandler = new InputHandler(mockScanner, inputValidator);

        //Act && Assert
        assertThrows(IllegalSizeValueException.class, () -> inputHandler.getSize());
    }

    @Test
    void testGetSize_RightEnter_Size() {
        //Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn("5 5");
        inputHandler = new InputHandler(mockScanner, inputValidator);
        Size expectedSize = new Size(5, 5);

        //Act
        Size actualSize = inputHandler.getSize();

        //Assert
        assertEquals(expectedSize, actualSize);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "42",
        "9 3 33",
        "k22",
        "-3",
        "0 2fl"
    })
    void testGetCoordinate_UnformattedInput_UnformattedInputExceptionThrown(String line) {
        //Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn(line);
        inputHandler = new InputHandler(mockScanner, inputValidator);

        //Act && Assert
        assertThrows(UnformattedInputException.class, () -> inputHandler.getCoordinate());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "5 5",
        "9 3",
        "34 2"
    })
    void testGetCoordinate_IllegalCoordinateValue_IllegalCoordinateValueExceptionThrown(String line) {
        //Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn(line);
        inputHandler = new InputHandler(mockScanner, inputValidator);

        //Act && Assert
        assertThrows(IllegalCoordinateValueException.class, () -> inputHandler.getCoordinate());
    }

    @Test
    void testGetCoordinate_RightEnter_Coordinate() {
        //Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn("2 3");
        inputHandler = new InputHandler(mockScanner, inputValidator);
        Coordinate expectedCoordinate = new Coordinate(2, 3);

        //Act
        Coordinate actualCoordinate = inputHandler.getCoordinate();

        //Assert
        assertEquals(expectedCoordinate, actualCoordinate);
    }

    @ParameterizedTest
    @ValueSource(strings = {"да", "нет"})
    void testGetPerfectFlag_ValidInput_ReturnsExpectedBoolean(String input) {
        // Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn(input);
        inputHandler = new InputHandler(mockScanner, inputValidator);

        boolean expected = input.equals("да");

        // Act
        boolean actual = inputHandler.getPerfectFlag();

        // Assert
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"maybe", "y", "1 2", ""})
    void testGetPerfectFlag_InvalidInput_ThrowsUnformattedInputException(String input) {
        // Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn(input);
        inputHandler = new InputHandler(mockScanner, inputValidator);

        // Act & Assert
        assertThrows(UnformattedInputException.class, () -> inputHandler.getPerfectFlag());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "4",
        "9",
        "x",
        ""
    })
    void testGetSolverType_InvalidInput_ThrowsNonGeneratorNumberException(String input) {
        // Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn(input);
        inputHandler = new InputHandler(mockScanner, inputValidator);

        // Act & Assert
        assertThrows(NonGeneratorNumberException.class, () -> inputHandler.getSolverType());
    }

    @Test
    void testGetSolverType_ValidInput_ReturnsSolverType() {
        // Arrange
        Mockito.when(mockScanner.nextLine()).thenReturn("1");
        inputHandler = new InputHandler(mockScanner, inputValidator);

        SolverType expectedType = SolverType.DIJKSTRA;

        // Act
        SolverType actualType = inputHandler.getSolverType();

        // Assert
        assertEquals(expectedType, actualType);
    }

    @BeforeEach
    void setUp() {
        inputValidator.currSize(new Size(5, 5));
    }
}
