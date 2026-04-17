package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.PhpDev;
import game.logic.entity.enemies.JavaDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.AllKnowingAi;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;

public class Level9 extends Level {
    public Level9() {
        super("Legacy Systems", "PHP's infinite resilience. Multiple predictive Java devs closing in.");
    }

    @Override
    public Board board(Player player, AIConfig aiConfig) {
        this.aiConfig = aiConfig;
        Board board = new Board(15, 13, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(2, 6));

        walls(board, 4, 2, 1, 3);
        walls(board, 4, 8, 1, 3);
        walls(board, 10, 1, 1, 2);
        walls(board, 10, 10, 1, 2);

        board.setupEnemy(new PhpDev(new NormalAi(board, aiConfig)), new Position(12, 6));
        board.setupEnemy(new JavaDev(new AllKnowingAi(board, aiConfig)), new Position(8, 2));
        board.setupEnemy(new JavaDev(new AllKnowingAi(board, aiConfig)), new Position(8, 10));

        board.searchEnemies();
        return board;
    }
}