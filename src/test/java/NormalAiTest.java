import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.Tile;
import game.logic.entity.enemies.Enemy;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NormalAiTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(10, 10, 1.0);
        Player player = new Player("P");
        board.setupPlayer(player, new Position(5, 5));
    }

    @Test
    void aiGetXpMultiplierReadsFromConfig() {
        Enemy enemy = new Enemy(50, 5, 1, "E");
        NormalAi ai = new NormalAi(board, AIConfig.NORMAL);
        ai.setBody(enemy);

        assertEquals(AIConfig.NORMAL.xpMultiplier, ai.getXpMultiplier(), 0.0001);
    }

    @Test
    void aiStartMultipliesStatsByConfig() {
        Enemy enemy = new Enemy(50, 10, 2, "E");
        Position p = new Position(3, 3);
        board.setTile(p, new Tile(enemy, p));

        NormalAi ai = new NormalAi(board, AIConfig.NORMAL);
        ai.setBody(enemy);

        double expectedAtk = enemy.getBaseAttack() * AIConfig.NORMAL.statsMultiplier;
        double expectedDef = enemy.getBaseDefense() * AIConfig.NORMAL.statsMultiplier;

        ai.start();
        ai.stop();

        assertEquals(expectedAtk, enemy.getBaseAttack(), 0.0001);
        assertEquals(expectedDef, enemy.getBaseDefense(), 0.0001);
        assertEquals(enemy.getMaxHp(), enemy.getHp(), 0.0001);
    }
}