import game.logic.board.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    void constructorFromPositionCopiesCoordinates() {
        Position original = new Position(3, 4);
        Position copy = new Position(original);

        assertEquals(original.x(), copy.x());
        assertEquals(original.y(), copy.y());
        assertEquals(original, copy);
    }

    @Test
    void upDecreasesY() {
        assertEquals(new Position(2, 4), new Position(2, 5).up());
    }

    @Test
    void downIncreasesY() {
        assertEquals(new Position(2, 6), new Position(2, 5).down());
    }

    @Test
    void leftDecreasesX() {
        assertEquals(new Position(1, 5), new Position(2, 5).left());
    }

    @Test
    void rightIncreasesX() {
        assertEquals(new Position(3, 5), new Position(2, 5).right());
    }

    @Test
    void addCombinesOffsets() {
        assertEquals(new Position(5, 8), new Position(2, 5).add(3, 3));
    }

    @Test
    void addWithNegativeOffsets() {
        assertEquals(new Position(-1, 2), new Position(2, 5).add(-3, -3));
    }

    @Test
    void hashCodeIsSumOfCoordinates() {
        assertEquals(7, new Position(3, 4).hashCode());
        assertEquals(0, new Position(0, 0).hashCode());
        assertEquals(-5, new Position(-3, -2).hashCode());
    }

    @Test
    void equalsForSameCoordinates() {
        assertEquals(new Position(1, 2), new Position(1, 2));
        assertNotEquals(new Position(1, 2), new Position(2, 1));
    }

    @Test
    void chainedMovements() {
        Position p = new Position(0, 0).right().right().down();
        assertEquals(new Position(2, 1), p);
    }
}