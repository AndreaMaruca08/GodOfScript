package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.readycomponents.CoolBorder;
import core.utilities.Dim;
import game.graphic.components.GraphicConsole;
import game.graphic.components.board.GraphicBoard;
import game.graphic.shared.Colors;
import game.logic.board.Board;
import game.logic.entity.player.Player;
import game.logic.entity.enemies.ai.AIConfig;
import game.logic.levels.Level;

import javax.swing.*;

public class GamePage extends ScalePage {
    private final Board board;
    private final Timer autoUpdate;

    public GamePage(ScaleUIApplication app, Player player, Level level, AIConfig difficulty) {
        super(app, "GamePage " + level.getName());
        board = level.board(player, difficulty);
        setBackground(Colors.PRIMARY);

        GraphicConsole console = new GraphicConsole(new Dim(0.5, 5, 25, 90), this, app, board);
        CoolBorder consoleBorder = new CoolBorder(console.getDim(), Colors.SECONDARY, 4);

        GraphicBoard boardGraphic = new GraphicBoard(new Dim(26, 6, 73, 93), board);

        createKey("ESC", () -> {stop(); app.changePage("LevelsPage");}, "ESCAPE");

        addScale(console);
        addScale(consoleBorder);
        addScale(boardGraphic);

        autoUpdate = new Timer(difficulty.delayMs, _ -> update(boardGraphic.getDim().bigger(0, 6)));
    }

    public void stop(){
        board.stop();
        autoUpdate.stop();
    }

    public void start(){
        board.start();
        autoUpdate.start();
    }

}
