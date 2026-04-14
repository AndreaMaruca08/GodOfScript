package game.logic.levels;

import game.logic.board.Board;
import game.logic.board.Tile;
import game.logic.entity.player.Player;
import game.logic.entity.enemies.ai.AIConfig;
import lombok.Data;

@Data
public abstract class Level {
    protected String name;
    protected String description;
    protected AIConfig aiConfig;

    public Level(String name, String description) {
        this.name = name;
        this.description = description;
        this.aiConfig = AIConfig.VERY_SLOW;
    }

    public abstract Board board(Player player, AIConfig aiConfig);

    protected static void walls(Board board, int x, int y, int width, int height){
        for(int i = x; i < x + width; i++){
            for(int j = y; j < y + height; j++){
                board.setTile(i, j, Tile.wall());
            }
        }
    }
}
