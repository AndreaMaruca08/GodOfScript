import core.ScaleUIApplication;
import game.graphic.GamePage;
import game.logic.entity.Player;
import game.logic.tasks.standard.Up;

void main() {
    ScaleUIApplication app = new ScaleUIApplication("God of Script");

    Player player = new Player();
    player.addTask(new Up());
    GamePage gamePage = new GamePage(app, player);

    app.addPage(gamePage);
    app.changePage(gamePage.getName());

    app.setVisible(true);
}