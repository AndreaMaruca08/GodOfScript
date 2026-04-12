package game.graphic.components;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.entity.Enemy;
import game.logic.entity.Entity;

import java.awt.*;

public class GraphicEntity extends ScaleComponent{
    private final Entity entity;
    private final boolean name;
    public GraphicEntity(Dim dim, Entity entity, boolean name) {
        super(dim, "GraphicEntity");
        this.entity = entity;
        this.name = name;
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.drawCircle(dim.smaller(0.1,0.1), entity.getColor());
        g.changeDrawWidth(0.12);
        g.drawCircleBorder(dim, Color.black);
        if(name){
            g.font(dim.width() * 0.15);
            g.drawText(dim.bigger(2,2), entity.getName(), Colors.TEXT);
        }

    }
}
