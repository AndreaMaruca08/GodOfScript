package game.graphic.components.profile;

import core.components.ScaleComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.logic.entity.player.Player;

public class GraphicStats extends ScaleComponent {
    private final Player player;
    public GraphicStats(Dim dim, Player player) {
        super(dim, "GraphicStats");
        this.player = player;
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.font(1.5);
        g.drawRoundRect(dim, (int)dim.width()/10, Colors.PRIMARY.darker());
        g.drawTextLeft(dim, String.format("Level: %d", player.getLevel()), Colors.TEXT);
        g.drawTextLeft(dim.ifXY(dim.x(), dim.y() + 3), String.format("XP: %.1f/%.1f", player.getXp(), player.getNextLevelXp()), Colors.TEXT);
        g.drawTextLeft(dim.ifXY(dim.x(), dim.y() + 6), String.format("Points: %d", player.getPoints()), Colors.TEXT);
        g.drawTextLeft(dim.ifXY(dim.x(), dim.y() + 12), String.format("Max HP:       %.1f", player.getMaxHp()), Colors.TEXT);
        g.drawTextLeft(dim.ifXY(dim.x(), dim.y() + 15), String.format("Base Attack:  %.1f", player.getBaseAttack()), Colors.TEXT);
        g.drawTextLeft(dim.ifXY(dim.x(), dim.y() + 18), String.format("Base Defense: %.1f", player.getBaseDefense()), Colors.TEXT);
    }
}
