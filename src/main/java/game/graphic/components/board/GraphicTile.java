package game.graphic.components.board;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.components.GraphicEntity;
import game.graphic.shared.Colors;
import game.logic.board.Tile;

public class GraphicTile extends ScaleComponent {
    private final Tile tile;
    private final boolean name;
    public GraphicTile(Dim dim, Tile tile, boolean name) {
        super(dim,"GraphicTile");
        this.tile = tile;
        this.name = name;
    }

    @Override
    public void draw(ScaleGraphic g) {
        if(tile.isEmpty() && !tile.isTargeted())
            return;
        if(tile.isTargeted()){
            g.drawRect(dim, Colors.HIGHLIGHT);
            tile.setTargeted(false);
        }
        if(tile.isWall())
            g.drawRect(dim, Colors.WALL);
        else if(tile.getEntity() != null)
            g.draw(new GraphicEntity(dim, tile.getEntity(), name));

    }
}
