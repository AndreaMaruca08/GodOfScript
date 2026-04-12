package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.Tile;
import game.logic.entity.Player;
import game.logic.entity.enemies.JuniorDev;

public class Level1 extends Level {

    public Level1() {
        super("TEST", "DESC");
    }

    @Override
    public Board board(Player player) {
        Board board = new Board(22, 20);

        board.setupPlayer(player, new Position(5, 5));

        board.setTile(15, 5, new Tile(new JuniorDev()));
        board.setTile(16, 6, new Tile(new JuniorDev()));
        board.setTile(17, 7, new Tile(new JuniorDev()));

        return board;
    }
}
