package game.graphic.components.board;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.entity.Player;

public class GraphicPlayerStatus extends ScaleComponent {
    private final Player player;

    private final Dim HEALTH_BAR;
    private final Dim DETAILS;

    public GraphicPlayerStatus(Dim dim, Player player) {
        super(dim);
        this.player = player;
        this.HEALTH_BAR = new Dim(dim.x(), dim.y(), dim.width(), dim.height()/2.2);
        this.DETAILS = new Dim(HEALTH_BAR.x(),  dim.y() + dim.height()/2.1, HEALTH_BAR.width(), dim.height()/2);
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.drawRoundRect(HEALTH_BAR, 3, player.getColor());
        g.drawRoundRectBorder(HEALTH_BAR, 3, player.getColor().darker().darker());
        g.font(1.5);
        g.drawTextLeft(HEALTH_BAR.ifXY(HEALTH_BAR.x() - 3.5, HEALTH_BAR.y() - 0.7), "HP", Colors.TEXT);
        g.font(0.9);
        g.drawTextLeft(HEALTH_BAR, player.getHp() + "/" + player.getMaxHp(), Colors.TEXT);

        g.drawText(DETAILS, "Lvl " + player.getLevel() +
                "   XP: " + player.getXp() + "/" + player.getNextLevelXp() +
                "    Points: " + player.getPoints(), Colors.TEXT);
    }
}
