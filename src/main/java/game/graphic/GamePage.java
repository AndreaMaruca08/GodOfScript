package game.graphic;

import core.ScaleUIApplication;
import core.components.ScalePage;
import core.readycomponents.CoolBorder;
import core.utilities.Dim;
import game.graphic.board.GraphicBoard;
import game.graphic.shared.Colors;
import game.logic.board.Board;
import game.logic.entity.Enemy;
import game.logic.entity.Player;
import game.logic.levels.Level1;

import java.awt.*;

public class GamePage extends ScalePage {
    private final Enemy[] enemies;
    private final Player player;

    public GamePage(ScaleUIApplication app, Player player, Enemy... enemies) {
        super(app, "GamePage");
        final Board board = Level1.board;
        this.player = player;
        this.enemies = enemies;
        setBackground(new Color(34, 34, 34));
        setFont(new Font("monospaced", Font.PLAIN, 21));

        GraphicConsole console = new GraphicConsole(new Dim(0.5, 5, 25, 90), this, player, board);
        CoolBorder consoleBorder = new CoolBorder(console.getDim(), Colors.SECONDARY, 4);

        GraphicBoard boardGraphic = new GraphicBoard(new Dim(26, 1, 73, 99), board);

        addScale(console);
        addScale(consoleBorder);
        addScale(boardGraphic);
    }
}
