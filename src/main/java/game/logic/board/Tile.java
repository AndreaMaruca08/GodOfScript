package game.logic.board;

import game.logic.entity.Entity;
import lombok.Data;

@Data
public class Tile {
    private boolean isWall;
    private boolean isOccupied;
    private Entity entity;

    private Tile(boolean isWall, boolean isOccupied, Entity entity) {
        this.isWall = isWall;
        this.isOccupied = isOccupied;
        this.entity = entity;
    }

    public Tile(boolean isWall) {
        this(isWall, true, null);
    }

    public Tile(Entity entity, Position position) {
        this(false, true, entity);
        entity.setPosition(position);
    }

    public boolean isEmpty(){
        return !isOccupied || !isWall && entity == null;
    }

    public static Tile empty(){
        return new Tile(false, false, null);
    }
    public static Tile wall(){
        return new Tile(true, true, null);
    }
}
