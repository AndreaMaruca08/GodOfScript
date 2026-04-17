package game.graphic.components.profile;

import core.components.ScalePage;
import core.components.ScaleUpdatableComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.logic.entity.player.Player;
import game.logic.entity.player.TypeOfUpgrade;

import java.util.List;

public class GraphicPointSystem extends ScaleUpdatableComponent {
    private final List<SingleStat> stats;

    public GraphicPointSystem(Dim dim, ScalePage page, Player player) {
        super(dim, page, "GraphicPointSystem");
        var hp = new SingleStat(new Dim(dim.x(), dim.y() + 5, dim.width(), 5), page,
                "Max HP", ()-> player.upgradeStat(TypeOfUpgrade.HP));
        var atk = new SingleStat(new Dim(dim.x(), dim.y() + 12, dim.width(), 5), page,
                "Base attack", ()-> player.upgradeStat(TypeOfUpgrade.ATTACK));
        var def = new SingleStat(new Dim(dim.x(), dim.y() + 19, dim.width(), 5), page,
                "Base defense", ()-> player.upgradeStat(TypeOfUpgrade.DEFENSE));

        stats = List.of(hp, atk, def);

        for (SingleStat stat : stats) {
            page.addScale(stat);
        }
    }

    @Override
    public void draw(ScaleGraphic g) {

    }
}
