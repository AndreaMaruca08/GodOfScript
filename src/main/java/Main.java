import core.ScaleUIApplication;
import game.graphic.pages.LevelsPage;
import game.logic.entity.Player;
import game.logic.entity.data.DataSaver;

void main() {
    ScaleUIApplication app = new ScaleUIApplication("God of Script");
    Player player;

    try {
        player = DataSaver.loadPlayer();
    }catch (Exception e){
        System.out.println("No save file found, starting a new game.");
        player = new Player();
        DataSaver.savePlayer(player);
    }

    LevelsPage levelsPage = new LevelsPage(app, player);
    app.addPage(levelsPage);
    app.changePage(levelsPage.getPageName());

    app.setVisible(true);
}