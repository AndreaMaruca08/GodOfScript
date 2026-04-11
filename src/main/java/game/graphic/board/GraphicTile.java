package game.graphic.board;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.GraphicEntity;
import game.graphic.shared.Colors;
import game.logic.board.Tile;

public class GraphicTile extends ScaleComponent {
    private final Tile tile;
    public GraphicTile(Dim dim, Tile tile) {
        super(dim,"GraphicTile");
        this.tile = tile;
    }

    @Override
    public void draw(ScaleGraphic g) {
        if(tile.isWall())
            g.drawRect(dim, Colors.WALL);
        else if(tile.getEntity() != null)
            g.draw(new GraphicEntity(dim, tile.getEntity()));
        else
            g.drawRect(dim, Colors.PRIMARY);
    }
}
