import core.ScaleUIApplication;
import game.graphic.pages.LevelsPage;
import game.logic.entity.player.Player;
import game.logic.entity.player.DataSaver;

import javax.swing.*;

void main() {
    ScaleUIApplication app = new ScaleUIApplication("God of Script");

    Player player;
    try {
        player = DataSaver.loadPlayer();
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Starting a new game");
        String playerName = JOptionPane.showInputDialog("Enter your name:");
        if(playerName == null)
            System.exit(0);
        player = new Player(playerName);
        DataSaver.savePlayer(player);
    }

    LevelsPage levelsPage = new LevelsPage(app, player);
    app.addPage(levelsPage);
    app.changePage(levelsPage.getPageName());

    app.setVisible(true);
}