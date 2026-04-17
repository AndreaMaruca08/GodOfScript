package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.player.Player;
import game.logic.entity.enemies.JuniorDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.NormalAi;

public class Level2 extends Level {
    public Level2() {
        super("The Stack Overflow Generation", "Multiple junior devs - learn positioning.");
    }

    @Override
    public Board board(Player player, AIConfig config) {
        this.aiConfig = config;
        Board board = new Board(11, 9, aiConfig.xpMultiplier);

        board.setupPlayer(player, new Position(1, 4));

        // Due nemici su lati opposti - costringe il giocatore a scegliere
        board.setupEnemy(new JuniorDev(new NormalAi(board, aiConfig)), new Position(9, 2));
        board.setupEnemy(new JuniorDev(new NormalAi(board, aiConfig)), new Position(9, 6));

        board.searchEnemies();
        return board;
    }
}