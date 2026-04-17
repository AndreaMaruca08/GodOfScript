package game.graphic.components;

import core.ScaleUIApplication;

import core.components.ScalePressableComponent;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.pages.GamePage;
import game.graphic.shared.Colors;
import game.logic.entity.player.Player;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.levels.Level;

import javax.swing.*;

public class GraphicLevel extends ScalePressableComponent {
    private final ScaleUIApplication app;
    private final Level level;
    private final Player player;

    public GraphicLevel(Dim dim, Player player, Level level, ScaleUIApplication app) {
        super(dim, "GraphicLevel");
        this.app = app;
        this.level = level;
        this.player = player;
    }

    @Override
    public void press() {
        AIConfig[] opzioni = AIConfig.values();
        AIConfig scelta = (AIConfig) JOptionPane.showInputDialog(
                null,
                "Select difficulty, higher = more multiplier:",
                "Difficulty",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opzioni,
                opzioni[0]
        );

        if (scelta == null) return;

        final var game = new GamePage(app, player, level, scelta);
        app.addPage(game);
        app.changePage(game.getPageName());
        game.start();
    }

    @Override
    public void draw(ScaleGraphic g) {
        g.drawRoundRect(dim, 2, Colors.SECONDARY);
        g.drawText(dim, level.getName(), Colors.TEXT);
    }

}

