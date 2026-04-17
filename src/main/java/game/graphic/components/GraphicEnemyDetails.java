package game.graphic.components;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.components.profile.GraphicScriptList;
import game.graphic.shared.Colors;
import game.logic.entity.enemies.Enemy;

public class GraphicEnemyDetails extends ScalePage {
    private final Enemy enemy;
    public GraphicEnemyDetails(ScaleUIApplication app, Enemy enemy) {
        super(app, enemy.getName());
        setBackground(Colors.PRIMARY);
        this.enemy = enemy;

        GraphicScriptList scripts = new GraphicScriptList(new Dim(20, 5, 80, 85), enemy.getScripts(), 4);

        createKey("ESC", () -> app.changePage("EnemiesPage"), "ESCAPE");

        addScale(scripts);
    }

    @Override
    public void draw(ScaleGraphic g) {
        Dim dim = new Dim(0, 0, 100, 100);
        g.font(2);
        g.drawTextLeft(dim, enemy.getName(), Colors.TEXT);
        g.drawTextLeft(dim.lower(4), "HP:  "+enemy.getMaxHp(), Colors.TEXT);
        g.drawTextLeft(dim.lower(8), "ATK: "+enemy.getBaseAttack(), Colors.TEXT);
        g.drawTextLeft(dim.lower(12), "DEF: "+enemy.getBaseDefense(), Colors.TEXT);



    }
}
