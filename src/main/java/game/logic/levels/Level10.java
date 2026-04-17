package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.JavaDaddy;
import game.logic.entity.enemies.JavaDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.AllKnowingAi;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;

public class Level10 extends Level{

    public Level10() {
        super("The boss of java", "");
    }

    @Override
    public Board board(Player player, AIConfig aiConfig) {
        this.aiConfig = aiConfig;
        Board board = new Board(13, 11, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(3, 5));

        walls(board, 7, 7, 6, 1);
        walls(board, 7, 7, 1, 1);
        walls(board, 7, 9, 1, 1);

        walls(board, 7, 3, 6, 1);
        walls(board, 7, 3, 1, 1);
        walls(board, 7, 1, 1, 1);

        board.setupEnemy(new JavaDaddy(new NormalAi(board, aiConfig)), new Position(12, 5));

        board.setupEnemy(new JavaDev(new AllKnowingAi(board, aiConfig)), new Position(10, 8));
        board.setupEnemy(new JavaDev(new AllKnowingAi(board, aiConfig)), new Position(10, 10));

        board.setupEnemy(new JavaDev(new AllKnowingAi(board, aiConfig)), new Position(10, 0));
        board.setupEnemy(new JavaDev(new AllKnowingAi(board, aiConfig)), new Position(10, 2));

        board.searchEnemies();
        return board;
    }
}
