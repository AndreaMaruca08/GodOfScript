package game.graphic.components.board;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.entity.Player;

public class GraphicPlayerStatus extends ScaleComponent {
    private final Player player;

    private final Dim healthBarDim;
    private final Dim healthDamage;

    public GraphicPlayerStatus(Dim dim, Player player) {
        super(dim);
        this.player = player;
        this.healthBarDim = new Dim(dim.x(), dim.y(), dim.width(), dim.height()/2);
        this.healthDamage = new Dim(dim.x(), dim.y() + dim.height()/2, dim.width(), dim.height()/2);
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.drawRoundRect(healthBarDim, 3, player.getColor());
        g.drawRoundRectBorder(healthBarDim, 3, player.getColor().darker().darker());
        g.font(1.5);
        g.drawTextLeft(healthBarDim.ifXY(healthBarDim.x() - 3.5, healthBarDim.y() - 0.7), "HP", Colors.TEXT);
        g.font(0.9);
        g.drawTextLeft(healthBarDim, player.getHp() + "/" + player.getMaxHp(), Colors.TEXT);
    }
}
