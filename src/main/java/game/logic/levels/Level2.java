package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.player.Player;
import game.logic.entity.enemies.JuniorDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.NormalAi;

public class Level2 extends Level{
    public Level2() {
        super("The junior devs",
                "A level against junior developers to learn the basics of the game."
        );
    }

    @Override
    public Board board(Player player, AIConfig config) {
        this.aiConfig = config;
        Board board = new Board(10, 8, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(1, 1));

        walls(board, 2, 2, 5, 1);
        walls(board, 2, 3, 1, 4);

        board.setupEnemy(new JuniorDev(new NormalAi(board, aiConfig)), new Position(8, 1));
        board.setupEnemy(new JuniorDev(new NormalAi(board, aiConfig)), new Position(3, 5));

        board.searchEnemies();
        return board;
    }
}
