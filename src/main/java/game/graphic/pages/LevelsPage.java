package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.readycomponents.ScaleButton;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.GraphicLevel;
import game.graphic.shared.Colors;
import game.logic.entity.player.Player;
import game.logic.levels.Level;
import game.logic.levels.Levels;

public class LevelsPage extends ScalePage {

    public LevelsPage(ScaleUIApplication app, Player player) {
        super(app, "LevelsPage");
        setBackground(Colors.PRIMARY);

        ScaleButton profileButton = new ScaleButton(new Dim(0, 0, 10, 3), "Profile", Colors.SECONDARY, Colors.TEXT);
        profileButton.setAction(() -> {app.addPage(new ProfilePage(app, player)); app.changePage("ProfilePage");});

        profileButton.setRounded(false);

        addScale(profileButton);
        initializeLevels(player);
    }

    @Override
    public void draw(ScaleGraphic g){
        g.font(2);
        g.drawText(new Dim(0, 1, 100, 5), "Levels", Colors.TEXT);
        g.font(0.7);
    }

    private void initializeLevels(Player player) {
        Dim start = new Dim(1, 5, 15, 5);
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
