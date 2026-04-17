package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.components.profile.GraphicScriptList;
import game.graphic.shared.Colors;
import game.logic.entity.Entity;
import game.logic.entity.player.Player;

public class GraphicScriptListPage extends ScalePage {
    private final Entity entity;
    private static final Dim TITLE = new Dim(0, 0, 100, 5);

    public GraphicScriptListPage(ScaleUIApplication app, Entity entity) {
        super(app, "GraphicScriptListPage");
        this.entity = entity;
        setBackground(Colors.PRIMARY);

        GraphicScriptList graphicScriptList = new GraphicScriptList(
                new Dim(0,10,100,90),
                entity.getScripts(), 5
        );

        createKey("ESC", () -> {
                    if (entity instanceof Player) {
                        app.changePage("ProfilePage");
                    } else {
                        app.changePage("EnemiesPage");
                    }
                }, "ESCAPE"
        );

        addScale(graphicScriptList);
    }

    @Override
    public void draw(ScaleGraphic g){
        g.font(3);
        g.drawText(TITLE, "Dettagli script di " + entity.getName(), Colors.TEXT);

        g.font(1);
    }
}
