package game.graphic.components.profile;

import core.components.ScaleComponent;
import core.components.ScalePage;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.logic.entity.player.Player;

public class GraphicPlayer extends ScaleComponent {
    private final GraphicStats stats;
    private final GraphicPointSystem points;
    public GraphicPlayer(Dim dim, ScalePage page, Player player) {
        super(dim, "GraphicPlayer");
        this.stats = new GraphicStats(new Dim(2,5,35, 70), player);
        this.points = new GraphicPointSystem(new Dim(60, 0, 38, 70), page, player);
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.draw(stats);
        g.draw(points);
    }
}
