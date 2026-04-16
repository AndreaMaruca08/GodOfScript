package game.graphic.components;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.entity.Entity;

import java.awt.*;

public class GraphicEntity extends ScaleComponent{
    private final Entity entity;
    private final boolean name;

    private final Dim BARRA;
    private final Dim HP;

    public GraphicEntity(Dim dim, Entity entity, boolean name) {
        super(dim, "GraphicEntity");
        this.entity = entity;
        this.name = name;
        BARRA = new Dim(dim.x() + dim.width()/5.5, dim.y()+ dim.height()*0.3, dim.width()/1.5, 1);
        HP = new Dim(BARRA.x(), BARRA.y() + BARRA.height()*0.10, BARRA.width(), BARRA.height());
    }

    @Override
    public void draw(ScaleGraphic g) {
        double scale = BARRA.width() * 0.13;
        g.drawCircle(dim.smaller(0.1,0.1), entity.getDisplayColor());
        g.changeDrawWidth(0.12);
        g.drawCircleBorder(dim, Color.black);
        if(name){
            g.font(dim.width() * 0.15);
            g.drawText(dim.bigger(BARRA.width() * 0.15,BARRA.width() * 0.15), entity.getName(), Colors.TEXT);
            g.drawRoundRect(BARRA, 1, entity.getColor().darker().darker().darker());
            g.drawRoundRect(
                    new Dim(BARRA.x(), BARRA.y(), BARRA.width() * entity.getHpPercentage(), BARRA.height()),
                    1, entity.getColor());
            g.font(scale);
            g.drawText(HP, String.format("%.1f", entity.getHp()), Colors.TEXT);
            g.drawRoundRectBorder(BARRA, 1, Color.black);
        }

    }
}
