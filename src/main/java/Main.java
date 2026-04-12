import core.ScaleUIApplication;
import game.graphic.pages.LevelsPage;
import game.logic.entity.Player;

void main() {
    ScaleUIApplication app = new ScaleUIApplication("God of Script");

    LevelsPage levelsPage = new LevelsPage(app, new Player());
    app.addPage(levelsPage);
    app.changePage(levelsPage.getName());

    app.setVisible(true);
}