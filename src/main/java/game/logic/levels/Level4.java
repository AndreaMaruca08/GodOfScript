package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.JavaScriptDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;

public class Level4 extends Level{
    public Level4() {
        super("Frontend bullshit",
                "Frontend sucks.");
    }

    @Override
    public Board board(Player player, AIConfig aiConfig) {
        this.aiConfig = aiConfig;
        Board board = new Board(22, 20, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(1, 1));

        walls(board, 2, 0, 1, 8);
        walls(board, 8, 0, 1, 8);
        walls(board, 14, 0, 1, 8);
        walls(board, 20, 0, 1, 8);

        walls(board, 5, 14, 1, 6);
        walls(board, 11, 14, 1, 6);
        walls(board, 17, 14, 1, 6);

        Position enemyPos = new Position(20, 10);
        Position enemies2Pos = new Position(10, 15);

        board.setupEnemy(new JavaScriptDev(new NormalAi(board, aiConfig)), enemyPos);
        board.setupEnemy(new JavaScriptDev(new NormalAi(board, aiConfig)), enemyPos.down());

        board.searchEnemies();
        return board;
    }
}
