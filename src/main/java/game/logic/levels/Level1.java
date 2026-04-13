package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.Tile;
import game.logic.entity.Player;
import game.logic.entity.enemies.JuniorDev;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.entity.enemies.ai.NormalAi;

public class Level1 extends Level {

    public Level1(AIConfig config) {
        super("TEST", "DESC", config);
    }

    @Override
    public Board board(Player player) {
        Board board = new Board(22, 20);

        board.setupPlayer(player, new Position(5, 5));

        board.setTile(
                15, 5,
                new Tile(
                        new JuniorDev(new NormalAi(board, aiConfig)),
                        new Position(15, 5)
                )
        );
        board.setTile(
                16, 6,
                new Tile(
                        new JuniorDev(new NormalAi(board, aiConfig)),
                        new Position(16, 6)
                )
        );
        board.setTile(
                17, 7,
                new Tile(
                        new JuniorDev(new NormalAi(board, aiConfig)),
                        new Position(17, 7)
                )
        );

        board.searchEnemies();
        return board;
    }
}
