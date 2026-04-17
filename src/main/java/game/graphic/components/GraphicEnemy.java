package game.graphic.components;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.entity.enemies.Enemy;

public class GraphicEnemy extends ScaleComponent {
    private final Dim btnSection;

    public GraphicEnemy(Dim dim) {
        super(dim, "GraphicEnemy");
        btnSection = dim.lower(2);
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.changeDrawWidth(0.20);
        g.drawRoundRect(btnSection, 2, Colors.SECONDARY.darker().darker());
        g.drawRoundRectBorder(btnSection, 2, Colors.PRIMARY.darker().darker());
        g.drawText(btnSection.lower(3.7), "Press for details", Colors.TEXT);
        g.font(0.8);
    }
}
