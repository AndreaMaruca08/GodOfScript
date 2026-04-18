import org.junit.jupiter.api.Test;
import game.logic.board.Position;
import game.logic.entity.Entity;
import game.logic.board.Tile;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    void emptyFactoryCreatesEmptyNonWallTile() {
        Tile tile = Tile.empty();

        assertFalse(tile.isWall());
        assertFalse(tile.isOccupied());
        assertNull(tile.getEntity());
        assertFalse(tile.isExploded());
        assertFalse(tile.isTargeted());
    }

    @Test
    void wallFactoryCreatesOccupiedWallTile() {
        Tile tile = Tile.wall();

        assertTrue(tile.isWall());
        assertTrue(tile.isOccupied());
        assertNull(tile.getEntity());
    }

    @Test
    void entityConstructorSetsEntityAndPosition() {
        Entity entity = new Entity(10, 1, 1, "E");
        Position position = new Position(3, 4);

        Tile tile = new Tile(entity, position);

        assertFalse(tile.isWall());
        assertTrue(tile.isOccupied());
        assertSame(entity, tile.getEntity());
        assertEquals(position, entity.getPosition());
    }

    @Test
    void emptyTileIsEmpty() {
        assertTrue(Tile.empty().isEmpty());
    }

    @Test
    void wallTileIsNotEmpty() {
        assertFalse(Tile.wall().isEmpty());
    }

    @Test
    void tileWithEntityIsNotEmpty() {
        Entity entity = new Entity(10, 1, 1, "E");
        Tile tile = new Tile(entity, new Position(0, 0));

        assertFalse(tile.isEmpty());
    }

    @Test
    void settersUpdateTileState() {
        Tile tile = Tile.empty();
        tile.setExploded(true);
        tile.setTargeted(true);

        assertTrue(tile.isExploded());
        assertTrue(tile.isTargeted());
    }
}