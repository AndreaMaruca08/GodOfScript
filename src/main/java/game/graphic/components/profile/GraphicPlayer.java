package game.graphic.components.profile;

import core.components.ScaleComponent;
import core.components.ScalePage;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.logic.entity.Player;

public class GraphicPlayer extends ScaleComponent {
    private final GraphicStats stats;
    private final GraphicPointSystem points;
    public GraphicPlayer(Dim dim, ScalePage page, Player player) {
        super(dim, "GraphicPlayer");
        this.stats = new GraphicStats(dim, player);
        this.points = new GraphicPointSystem(new Dim(30, 5, 70, 95), page, player);
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.draw(stats);
        g.draw(points);
    }
}
