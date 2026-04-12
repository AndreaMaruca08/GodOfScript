package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Tile;
import game.logic.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Level {
    protected String name;
    protected String description;

    public abstract Board board(Player player);

    protected static void walls(Board board, int x, int y, int width, int height){
        for(int i = x; i < x + width; i++){
            for(int j = y; j < y + height; j++){
                board.setTile(i, j, Tile.wall());
            }
        }
    }
}
