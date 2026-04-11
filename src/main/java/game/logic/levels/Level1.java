package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Tile;

public class Level1 {
    public static final Board board = new Board(30, 30);

     static {
        for (int i = 0; i < board.getWidth(); i++) {
            board.setTile(i, 0, Tile.wall());
            board.setTile(i, board.getHeight() - 1, Tile.wall());
        }
        for (int j = 0; j < board.getHeight(); j++) {
            board.setTile(0, j, Tile.wall());
            board.setTile(board.getWidth() - 1, j, Tile.wall());
        }
    }
}
