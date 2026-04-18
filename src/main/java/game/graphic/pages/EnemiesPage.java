package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.readycomponents.ScaleButton;
import core.utilities.Dim;
import game.graphic.components.GraphicEnemy;
import game.graphic.components.GraphicEnemyDetails;
import game.graphic.shared.Colors;
import game.logic.entity.enemies.Enemies;
import game.logic.entity.enemies.Enemy;
import game.logic.sound.Sounds;

public class EnemiesPage extends ScalePage {

    public EnemiesPage(ScaleUIApplication app) {
        super(app, "EnemiesPage");
        setBackground(Colors.PRIMARY);

        setupEnemies();

        createKey("ESC", () -> app.changePage("LevelsPage"), "ESCAPE");

    }

    private void setupEnemies() {
        int scriptWidth = 18;
        int scriptHeight = 9;
        int spacingX = 2;
        int spacingY = 3;

        for(int i = 0; i < Enemies.ENEMIES.size(); i++){
            Enemy enemy = Enemies.ENEMIES.get(i);

            int row = i / 5;
            int col = i % 5;

            double x = 1 + (col * (scriptWidth + spacingX));
            double y = 10 + (row * (scriptHeight + spacingY));

            Dim enemyDim = new Dim(x, y, scriptWidth, scriptHeight);

            addScale(new GraphicEnemy(enemyDim));

            ScaleButton btnDetails = getBtnDetails(enemyDim, enemy);
            addScale(btnDetails);
        }
    }

    private ScaleButton getBtnDetails(Dim enemyDim, Enemy enemy) {
        ScaleButton btnDetails = new ScaleButton(
                enemyDim,
                enemy.getName(),
                Colors.SECONDARY.darker(),
                Colors.TEXT
        );
        btnDetails.setAction(() -> {
            Sounds.clickSound();
            GraphicEnemyDetails detailsPage = new GraphicEnemyDetails(app, enemy);
            app.addPage(detailsPage);
            app.changePage(detailsPage.getPageName());
        });
        btnDetails.setRounded(false);
        return btnDetails;
    }
}
