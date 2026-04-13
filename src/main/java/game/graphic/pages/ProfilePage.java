package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.components.profile.GraphicPlayer;
import game.graphic.shared.Colors;
import game.logic.entity.Player;

public class ProfilePage extends ScalePage {
    private final static Dim TITLE = new Dim(0, 0, 100, 5);
    private final Player player;

    public ProfilePage(ScaleUIApplication app, Player player) {
        super(app, "ProfilePage");
        setBackground(Colors.PRIMARY);

        this.player = player;
        createKey("ESC", () -> app.changePage("LevelsPage"), "ESCAPE", "BACKSPACE");

        GraphicPlayer playerGraphic = new GraphicPlayer(new Dim(0, 5, 100, 95), this, player);
        addScale(playerGraphic);

    }
    @Override
    public void draw(ScaleGraphic g) {
        g.font(2);
        g.drawText(TITLE, player.getName(), Colors.TEXT);
    }
}
