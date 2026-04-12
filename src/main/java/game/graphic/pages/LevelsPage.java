package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.utilities.Dim;
import game.graphic.GraphicLevel;
import game.graphic.shared.Colors;
import game.logic.entity.Player;
import game.logic.levels.Level;
import game.logic.levels.Levels;

public class LevelsPage extends ScalePage {

    public LevelsPage(ScaleUIApplication app, Player player) {
        super(app, "LevelsPage");
        setBackground(Colors.PRIMARY);

        initializeLevels(player);
    }

    private void initializeLevels(Player player) {
        Dim start = new Dim(1, 1, 15, 5);
        int count = 0;
        for (Level lvl : Levels.LEVELS) {
            addScale(new GraphicLevel(start, player, lvl, app));
            count++;
            if (count % 5 == 0) {
                start = new Dim(1, start.y() + 6, start.width(), start.height());
            } else {
                start = new Dim(start.x() + 16, start.y(), start.width(), start.height());
            }
        }
    }
}
