package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.RustDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;

public class Level7 extends Level {
    public Level7() {
        super("The Borrow Checker", "Rust's fortress. Built for defense. One unbeatable tank.");
    }

    @Override
    public Board board(Player player, AIConfig aiConfig) {
        this.aiConfig = aiConfig;
        Board board = new Board(13, 11, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(1, 5));

        walls(board, 6, 3, 1, 5);
        walls(board, 8, 1, 1, 1);
        walls(board, 8, 9, 1, 1);
        walls(board, 10, 4, 1, 3);

        board.setupEnemy(new RustDev(new NormalAi(board, aiConfig)), new Position(11, 5));

        board.searchEnemies();
        return board;
    }
}