package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.CppDev;
import game.logic.entity.enemies.JavaDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.AllKnowingAi;
import game.logic.entity.enemies.ai.NormalAi;
import game.logic.entity.player.Player;

public class Level8 extends Level {
    public Level8() {
        super("Enterprise Nightmare", "C++ DPS god + Java professional. The pressure mounts.");
    }

    @Override
    public Board board(Player player, AIConfig aiConfig) {
        this.aiConfig = aiConfig;
        Board board = new Board(15, 13, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(1, 6));

        walls(board, 7, 0, 1, 13);
        walls(board, 3, 4, 1, 5);
        walls(board, 11, 4, 1, 5);

        board.setupEnemy(new CppDev(new NormalAi(board, aiConfig)), new Position(5, 6));
        board.setupEnemy(new JavaDev(new AllKnowingAi(board, aiConfig)), new Position(13, 6));

        board.searchEnemies();
        return board;
    }
}