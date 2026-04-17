package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.JavaScriptDev;
import game.logic.entity.enemies.PythonDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.AllKnowingAi;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;

public class Level6 extends Level {
    public Level6() {
        super("Polyglot Ambush", "Mix of different devs. One is predictive!");
    }

    @Override
    public Board board(Player player, AIConfig aiConfig) {
        this.aiConfig = aiConfig;
        Board board = new Board(13, 11, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(1, 5));

        walls(board, 4, 2, 1, 1);
        walls(board, 4, 8, 1, 1);
        walls(board, 8, 4, 1, 3);

        board.setupEnemy(new JavaScriptDev(new NormalAi(board, aiConfig)), new Position(11, 2));
        board.setupEnemy(new PythonDev(new AllKnowingAi(board, aiConfig)), new Position(11, 8));

        board.searchEnemies();
        return board;
    }
}