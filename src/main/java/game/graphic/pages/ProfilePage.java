package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.readycomponents.ScaleButton;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.components.profile.GraphicPlayer;
import game.graphic.shared.Colors;
import game.logic.entity.player.Player;

public class ProfilePage extends ScalePage {
    private final static Dim TITLE = new Dim(0, 0, 100, 5);
    private final Player player;

    public ProfilePage(ScaleUIApplication app, Player player) {
        super(app, "ProfilePage");
        this.player = player;
        setBackground(Colors.PRIMARY);

        createKey("ESC", () -> app.changePage("LevelsPage"), "ESCAPE", "BACKSPACE");

        GraphicPlayer playerGraphic = new GraphicPlayer(new Dim(0, 5, 100, 95), this, player);
        ScaleButton btnScripts = new ScaleButton(new Dim(3, 40, 20, 5), "Scripts detail", Colors.PRIMARY.brighter(), Colors.TEXT);
        btnScripts.setRounded(false);
        btnScripts.setAction(() -> {
            GraphicScriptListPage page = new GraphicScriptListPage(app, player);
            app.addPage(page);
            app.changePage(page.getPageName());
        });

        addScale(playerGraphic);
        addScale(btnScripts);
    }
    @Override
    public void draw(ScaleGraphic g) {
        g.font(1.5);
        g.drawText(TITLE, player.getName(), Colors.TEXT);
    }
}
