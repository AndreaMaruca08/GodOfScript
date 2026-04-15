import core.ScaleUIApplication;
import game.graphic.pages.TitlePage;
import game.logic.entity.player.Player;
import game.logic.entity.player.DataSaver;

import javax.swing.*;

void main() {
    ScaleUIApplication app = new ScaleUIApplication("God of Script");

    Player player = DataSaver.getPlayer();

    TitlePage title = new TitlePage(app, player);
    app.addPage(title);
    app.changePage(title.getPageName());

    app.setVisible(true);
}