import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.Tile;
import game.logic.board.exceptions.DeadPlayer;
import game.logic.entity.Entity;
import game.logic.entity.enemies.Enemy;
import game.logic.entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardDamageTest {

    private Board board;
    private Player player;

    @BeforeEach
    void setUp() {
        board = new Board(6, 6, 1.0);
        player = new Player("P");
        board.setupPlayer(player, new Position(0, 0));
    }

    @Test
    void damageToReducesHp() {
        Entity target = new Entity(100, 0, 0, "Target");
        Position p = new Position(2, 2);
        board.setTile(p, new Tile(target, p));

        board.damageTo(p, 30);

        assertEquals(70, target.getHp(), 0.0001);
        assertFalse(target.isDead());
    }

    @Test
    void damageToEmptyTileDoesNothing() {
        assertDoesNotThrow(() -> board.damageTo(new Position(3, 3), 50));
    }

    @Test
    void damageToPlayerKillsAndThrowsDeadPlayer() {
        assertThrows(DeadPlayer.class, () ->
                board.damageTo(new Position(0, 0), 10_000)
        );
    }

    @Test
    void damageToPlayerMethodIgnoresEnemies() {
        Enemy enemy = new Enemy(50, 0, 0, "EnemyTest");
        Position p = new Position(2, 2);
        board.setTile(p, new Tile(enemy, p));
        board.getEnemies().add(enemy);

        board.damageToPlayer(p, 1000);

        assertEquals(50, enemy.getHp(), 0.0001);
    }

    @Test
    void damageToPlayerHitsPlayer() {
        board.damageToPlayer(new Position(0, 0), 5);
        assertTrue(player.getHp() < player.getMaxHp());
    }
}