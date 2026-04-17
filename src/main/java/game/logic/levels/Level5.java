package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.CDev;
import game.logic.entity.enemies.GoDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;

public class Level5 extends Level {
    public Level5() {
        super("Memory Wars", "C devs know memory. Two strong foes with walls between.");
    }

    @Override
    public Board board(Player player, AIConfig aiConfig) {
        this.aiConfig = aiConfig;
        Board board = new Board(13, 11, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(1, 5));

        walls(board, 6, 0, 1, 11);
        walls(board, 3, 3, 1, 2);
        walls(board, 9, 6, 1, 2);

        board.setupEnemy(new CDev(new NormalAi(board, aiConfig)), new Position(4, 3));
        board.setupEnemy(new GoDev(new NormalAi(board, aiConfig)), new Position(11, 7));

        board.searchEnemies();
        return board;
    }
}