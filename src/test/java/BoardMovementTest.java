import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.Tile;
import game.logic.board.exceptions.OccupiedException;
import game.logic.board.exceptions.WallException;
import game.logic.entity.Entity;
import game.logic.entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardMovementTest {

    private Board board;
    private Player player;

    @BeforeEach
    void setUp() {
        board = new Board(6, 6, 1.0);
        player = new Player("P");
        board.setupPlayer(player, new Position(1, 1));
    }

    @Test
    void moveEntityToEmptyTileUpdatesTiles() {
        Position start = new Position(1, 1);
        Position end = new Position(2, 1);

        board.move(start, end);

        assertNull(board.getTile(start).getEntity());
        assertFalse(board.getTile(start).isOccupied());
        assertSame(player, board.getTile(end).getEntity());
        assertTrue(board.getTile(end).isOccupied());
        assertEquals(end, player.getPosition());
    }

    @Test
    void movingPlayerUpdatesPlayerPosition() {
        board.move(new Position(1, 1), new Position(3, 3));
        assertEquals(new Position(3, 3), board.getPlayerPosition());
    }

    @Test
    void moveFromEmptyTileDoesNothing() {
        assertDoesNotThrow(() ->
                board.move(new Position(0, 0), new Position(4, 4))
        );
        assertTrue(board.getTile(4, 4).isEmpty());
    }

    @Test
    void moveOntoWallThrowsWallException() {
        board.setTile(2, 2, Tile.wall());

        assertThrows(WallException.class, () ->
                board.move(new Position(1, 1), new Position(2, 2))
        );
    }

    @Test
    void moveOntoOccupiedTileThrowsOccupiedException() {
        Entity other = new Entity(10, 2, 1, "Other");
        Position occ = new Position(3, 3);
        board.setTile(occ, new Tile(other, occ));

        assertThrows(OccupiedException.class, () ->
                board.move(new Position(1, 1), occ)
        );
    }

    @Test
    void setupEnemyPlacesEnemyAndSetsPosition() {
        Entity entity = new Entity(10, 2, 1, "E");
        Position p = new Position(4, 4);
        board.setTile(p, new Tile(entity, p));

        assertEquals(p, entity.getPosition());
        assertSame(entity, board.getTile(p).getEntity());
    }
}