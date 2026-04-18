import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.Tile;
import game.logic.entity.Entity;
import game.logic.entity.player.Player;
import game.logic.scripts.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class BoardAoeAndLineTest {

    private Board board;
    private Player player;

    @BeforeEach
    void setUp() {
        board = new Board(8, 8, 1.0);
        player = new Player("P");
        board.setupPlayer(player, new Position(4, 4));
    }

    @Test
    void aoeActionReturnsNoEnemyWhenNoTargets() {
        Event result = board.aoeAction(new Position(0, 0), 1, false, _ ->  Event.OK);
        assertEquals(Event.NO_ENEMY, result);
    }

    @Test
    void aoeActionCallsActionOnOccupiedTiles() {
        Entity target = new Entity(100, 0, 0, "T");
        Position p = new Position(5, 4);
        board.setTile(p, new Tile(target, p));

        AtomicInteger calls = new AtomicInteger();
        Event result = board.aoeAction(new Position(4, 4), 1, false, _ -> {
            calls.incrementAndGet();
            return Event.OK;
        });

        assertTrue(calls.get() >= 1);
        assertEquals(Event.OK, result);
    }

    @Test
    void aoeActionSkipsCenter() {
        AtomicInteger callsOnCenter = new AtomicInteger();
        Position center = new Position(4, 4);

        board.aoeAction(center, 1, false, pos -> {
            if (pos.equals(center)) callsOnCenter.incrementAndGet();
            return Event.OK;
        });

        assertEquals(0, callsOnCenter.get());
    }

    @Test
    void aoeActionWithDisruptWallsClearsWalls() {
        Position wallPos = new Position(5, 4);
        board.setTile(wallPos, Tile.wall());

        board.aoeAction(new Position(4, 4), 1, true, _ -> Event.OK);

        assertFalse(board.getTile(wallPos).isWall());
    }

    @Test
    void aoeActionReturnsWinImmediately() {
        Entity target = new Entity(100, 0, 0, "T");
        Position p = new Position(5, 4);
        board.setTile(p, new Tile(target, p));

        Event result = board.aoeAction(new Position(4, 4), 1, false, _ -> Event.WIN);
        assertEquals(Event.WIN, result);
    }

    @Test
    void lineActionStopsOnWallWhenNotDisrupting() {
        Position wallPos = new Position(5, 4);
        board.setTile(wallPos, Tile.wall());

        AtomicInteger calls = new AtomicInteger();
        board.lineAction(player, "right", 3, false, _ -> {
            calls.incrementAndGet();
            return Event.OK;
        });

        // non deve oltrepassare il muro
        assertEquals(0, calls.get());
    }

    @Test
    void lineActionDisruptsWalls() {
        Position wallPos = new Position(5, 4);
        board.setTile(wallPos, Tile.wall());

        board.lineAction(player, "right", 3, true, _ -> Event.OK);

        assertFalse(board.getTile(wallPos).isWall());
    }

    @Test
    void lineActionReturnsDeadImmediately() {
        Entity target = new Entity(100, 0, 0, "T");
        Position p = new Position(5, 4);
        board.setTile(p, new Tile(target, p));

        Event result = board.lineAction(player, "right", 3, false, _ -> Event.DEAD);
        assertEquals(Event.DEAD, result);
    }
}