package game.graphic;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.logic.entity.Entity;

public class GraphicEntity extends ScaleComponent{
    private final Entity entity;
    public GraphicEntity(Dim dim, Entity entity) {
        super(dim, "GraphicEntity");
        this.entity = entity;
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.drawCircle(dim, entity.getColor());
        g.drawCircleBorder(dim, entity.getColor().darker());
    }
}
