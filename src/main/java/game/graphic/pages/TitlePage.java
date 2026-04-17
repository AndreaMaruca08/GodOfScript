package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.components.title.TitleButton;
import game.graphic.shared.Colors;
import game.logic.entity.player.DataSaver;
import game.logic.entity.player.Player;

import java.awt.*;

public class TitlePage extends ScalePage {
    public TitlePage(ScaleUIApplication app, Player player) {
        super(app, "TitlePage");
        setBackground(Colors.PRIMARY);

        LevelsPage levelsPage = new LevelsPage(app, player);
        app.addPage(levelsPage);
        EnemiesPage enemiesPage = new EnemiesPage(app);
        app.addPage(enemiesPage);

        TitleButton start = new TitleButton(
                new Dim(30, 30, 40, 7),
                "Continue: " + player.getName() + " LVL " + player.getLevel(), () -> app.changePage(levelsPage.getPageName()));
        TitleButton newGame = new TitleButton(
                new Dim(30, 38, 40, 7),
                "New game", () -> {
            Player newPlayer = DataSaver.createPlayer();
            LevelsPage newLevelsPage = new LevelsPage(app, newPlayer);
            app.addPage(newLevelsPage);
            app.changePage(newLevelsPage.getPageName());
        });
        TitleButton esci = new TitleButton(
                new Dim(30, 46, 40, 7),
                "Exit", () -> System.exit(0));

        addScale(newGame);
        addScale(start);
        addScale(esci);
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.font(5);
        g.drawText(new Dim(0,0,100,10), "> God of Script", new Color(100,150,100));
        g.font(1);
    }

}
