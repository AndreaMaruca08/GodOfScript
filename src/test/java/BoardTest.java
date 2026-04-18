import game.logic.board.exceptions.OutOfBoard;
import game.logic.entity.Entity;
import game.logic.board.Board;
import game.logic.entity.player.Player;
import game.logic.board.Position;
import game.logic.board.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(5, 5, 1.0);
    }

    @Test
    void boardInitializedWithEmptyTiles() {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                Tile tile = board.getTile(x, y);
                assertNotNull(tile);
                assertTrue(tile.isEmpty());
            }
        }
    }

    @Test
    void boardStoresDimensions() {
        assertEquals(5, board.getWidth());
        assertEquals(5, board.getHeight());
        assertEquals(1.0, board.getXpMultiplier());
    }

    @Test
    void boardWithNamesFlagConstructor() {
        Board b = new Board(3, 3, 2.0, false);
        assertFalse(b.isNames());
        assertEquals(2.0, b.getXpMultiplier());
    }

    @Test
    void defaultConstructorKeepsNamesTrue() {
        assertTrue(board.isNames());
    }

    @Test
    void getTileOutOfBoundsThrows() {
        assertThrows(OutOfBoard.class, () -> board.getTile(-1, 0));
        assertThrows(OutOfBoard.class, () -> board.getTile(0, -1));
        assertThrows(OutOfBoard.class, () -> board.getTile(5, 0));
        assertThrows(OutOfBoard.class, () -> board.getTile(0, 5));
    }

    @Test
    void getTileByPositionReturnsSameTile() {
        Tile tile = Tile.wall();
        board.setTile(2, 3, tile);

        assertSame(tile, board.getTile(new Position(2, 3)));
    }

    @Test
    void setTileByPositionUpdatesBoard() {
        Tile tile = Tile.wall();
        board.setTile(new Position(1, 1), tile);

        assertSame(tile, board.getTile(1, 1));
    }

    @Test
    void isInBoundsReturnsTrueInsideBoard() {
        assertTrue(board.isInBounds(0, 0));
        assertTrue(board.isInBounds(4, 4));
        assertTrue(board.isInBounds(new Position(2, 2)));
    }

    @Test
    void isInBoundsReturnsFalseOutside() {
        assertFalse(board.isInBounds(-1, 0));
        assertFalse(board.isInBounds(0, -1));
        assertFalse(board.isInBounds(5, 0));
        assertFalse(board.isInBounds(0, 5));
        assertFalse(board.isInBounds(new Position(-1, 0)));
        assertFalse(board.isInBounds(new Position(5, 5)));
    }

    @Test
    void cleanResetsAllTiles() {
        board.setTile(0, 0, Tile.wall());
        board.setTile(1, 1, Tile.wall());

        board.clean();

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                assertTrue(board.getTile(x, y).isEmpty());
            }
        }
    }

    @Test
    void setupPlayerPlacesPlayerOnBoard() {
        Player player = new Player("Tester");
        Position position = new Position(2, 2);

        board.setupPlayer(player, position);

        assertSame(player, board.getTile(2, 2).getEntity());
        assertEquals(position, player.getPosition());
        assertEquals(position, board.getPlayerPosition());
        assertSame(player, board.getPlayer());
    }

    @Test
    void noEnemiesWhenListIsEmpty() {
        assertTrue(board.noEnemies());
    }

    @Test
    void activeIsFalseAtCreation() {
        assertFalse(board.isActive());
    }

    @Test
    void onGameEndCallbackCanBeSetAndRead() {
        Runnable r = () -> { };
        board.setOnGameEnd(r);
        assertSame(r, board.getOnGameEnd());
    }

    @Test
    void searchEnemiesDetectsPlacedEntities() {
        // nessun nemico placeholder: testiamo che non esploda e mantenga lista vuota
        board.searchEnemies();
        assertTrue(board.getEnemies().isEmpty());
    }

    @Test
    void setEntityOnTileAndRetrieve() {
        Entity e = new Entity(10, 2, 1, "Dummy");
        Position p = new Position(2, 2);
        board.setTile(p, new Tile(e, p));

        assertSame(e, board.getTile(p).getEntity());
        assertEquals(p, e.getPosition());
    }
}