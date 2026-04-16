package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.CDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;

public class Level3 extends Level{
    public Level3() {
        super("The C supremacy",
                "C...");
    }

    @Override
    public Board board(Player player, AIConfig aiConfig) {
        this.aiConfig = aiConfig;
        Board board = new Board(10, 8, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(1, 3));

        walls(board, 1, 2, 8, 1);
        walls(board, 1, 5, 8, 1);

        Position enemyPos = new Position(8, 3);

        board.setupEnemy(new CDev(new NormalAi(board, aiConfig)), enemyPos);
        board.setupEnemy(new CDev(new NormalAi(board, aiConfig)), enemyPos.down());

        board.searchEnemies();
        return board;
    }
}
