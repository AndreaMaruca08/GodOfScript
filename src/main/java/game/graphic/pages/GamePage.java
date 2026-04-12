package game.graphic.pages;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.readycomponents.CoolBorder;
import core.utilities.Dim;
import game.graphic.components.GraphicConsole;
import game.graphic.components.board.GraphicBoard;
import game.graphic.shared.Colors;
import game.logic.board.Board;
import game.logic.entity.Player;
import game.logic.levels.Level;

import java.awt.*;

public class GamePage extends ScalePage {
    private final Board board;

    public GamePage(ScaleUIApplication app, Player player,  Level level) {
        super(app, "GamePage " + level.getName());
        board = level.board(player);
        setBackground(Colors.PRIMARY);

        GraphicConsole console = new GraphicConsole(new Dim(0.5, 5, 25, 90), this, player, board);
        CoolBorder consoleBorder = new CoolBorder(console.getDim(), Colors.SECONDARY, 4);

        GraphicBoard boardGraphic = new GraphicBoard(new Dim(26, 6, 73, 93), board);

        createKey("ESC", () -> {stop(); app.changePage("LevelsPage");}, "ESCAPE");

        addScale(console);
        addScale(consoleBorder);
        addScale(boardGraphic);
    }

    public void stop(){
        board.stop();
    }

    public void start(){
        board.start();
    }

}
