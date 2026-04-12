package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.Tile;
import game.logic.entity.Player;
import game.logic.entity.enemies.JuniorDev;

public class Level2 extends Level{
    public Level2() {
        super("The junior devs",
                "A level against junior developers to learn the basics of the game."
        );
    }

    @Override
    public Board board(Player player) {
        Board board = new Board(10, 8);

        board.setupPlayer(player, new Position(1, 1));

        walls(board, 2, 2, 4, 2);

        board.setTile(8, 1, new Tile(new JuniorDev()));
        board.setTile(5, 7, new Tile(new JuniorDev()));
        board.setTile(2, 5, new Tile(new JuniorDev()));

        return board;
    }
}
