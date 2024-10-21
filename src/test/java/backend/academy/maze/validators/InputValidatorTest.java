package backend.academy.maze.validators;

import backend.academy.maze.models.Coordinate;
import backend.academy.maze.models.Size;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    private final InputValidator validator = new InputValidator();

    @Test
    void testIsGeneratorNumber_ValidNumber() {
        assertTrue(validator.isGeneratorNumber("1"));
    }

    @Test
    void testIsGeneratorNumber_InvalidNumber() {
        assertFalse(validator.isGeneratorNumber("5"));
        assertFalse(validator.isGeneratorNumber("12"));
        assertFalse(validator.isGeneratorNumber("a"));
    }

    @Test
    void testContainsTwoNumbers_ValidInput() {
        assertTrue(validator.containsTwoNumbers("10 20"));
    }

    @Test
    void testContainsTwoNumbers_InvalidInput() {
        assertFalse(validator.containsTwoNumbers("10,20"));
        assertFalse(validator.containsTwoNumbers("abc"));
    }

    @Test
    void testIsCorrectSize_ValidSize() {
        Size size = new Size(5, 5);
        assertTrue(validator.isCorrectSize(size));
    }

    @Test
    void testIsCorrectSize_InvalidSize() {
        Size tooBig = new Size(100, 100);
        Size negative = new Size(-1, 5);
        assertFalse(validator.isCorrectSize(tooBig));
        assertFalse(validator.isCorrectSize(negative));
    }

    @Test
    void testIsCorrectCoordinate_ValidCoordinate() {
        validator.currSize(new Size(10, 10));
        Coordinate coordinate = new Coordinate(5, 5);
        assertTrue(validator.isCorrectCoordinate(coordinate));
    }

    @Test
    void testIsCorrectCoordinate_InvalidCoordinate() {
        validator.currSize(new Size(5, 5));
        Coordinate outOfBounds = new Coordinate(10, 10);
        assertFalse(validator.isCorrectCoordinate(outOfBounds));
    }

    @Test
    void testIsYesOrNoAnswer_ValidAnswer() {
        assertTrue(validator.isYesOrNoAnswer("да"));
        assertTrue(validator.isYesOrNoAnswer("нет"));
    }

    @Test
    void testIsYesOrNoAnswer_InvalidAnswer() {
        assertFalse(validator.isYesOrNoAnswer("yes"));
        assertFalse(validator.isYesOrNoAnswer("1"));
    }

    @Test
    void testIsSolverNumber_ValidNumber() {
        assertTrue(validator.isSolverNumber("1"));
    }

    @Test
    void testIsSolverNumber_InvalidNumber() {
        assertFalse(validator.isSolverNumber("5"));
        assertFalse(validator.isSolverNumber("12"));
        assertFalse(validator.isSolverNumber("b"));
    }
}
