package game.graphic.components;

import core.components.ScalePage;
import core.components.ScaleUpdatableComponent;
import core.readycomponents.ScaleTxtArea;
import core.utilities.Dim;
import core.utilities.ScaleGraphic;
import game.graphic.shared.Colors;
import game.graphic.shared.Fonts;
import game.logic.board.Board;
import game.logic.entity.Player;
import game.logic.tasks.ConsoleLog;
import game.logic.tasks.Event;
import game.logic.tasks.Console;
import lombok.Getter;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicConsole extends ScaleUpdatableComponent {
    private final ScaleTxtArea input;
    @Getter
    private final Console console;

    private Color status = Colors.SUCCESS;

    private final static String BASE = "> ";

    private final Dim STATE_DIM = new Dim(dim.x() + 2.5, this.dim.y() + dim.height()*0.08, 20,3);
    private final Dim NAME_DIM = new Dim(this.dim.x(), this.dim.y() - 4, 30,4);

    public GraphicConsole(Dim dim, ScalePage page, Player player, Board board) {
        super(dim, page, "GraphicConsole");
        this.input = new ScaleTxtArea(new Dim(dim.x(), dim.y(), dim.width(), dim.height()*0.08), Colors.PRIMARY, Colors.TEXT);
        this.console = new Console(player, board);
        setupEnterListener();
        input.setText(BASE);
    }

    private void setupEnterListener() {
        input.getTextArea().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    handleEnterPressed();
                }
            }
        });
    }

    private void handleEnterPressed() {
        String command = input.getTextArea().getText().trim();
        if (command.equals(BASE) || command.length() < 2)
            return;

        Event result = console.execute(command.substring(2));

        status = result == null ? Colors.ERROR : result.getResult();

        input.setText(BASE);

        updateAll();
    }

    @Override
    public void draw(ScaleGraphic g) {
        input.getTextArea().setFont(Fonts.PRIMARY(ScaleGraphic.getX(1.25, g.page())));
        g.font(1.1);
        g.drawTextLeft(NAME_DIM,"Console of " + console.getEntity().getName(), Colors.TEXT);
        g.draw(input);
        g.drawRoundRect(STATE_DIM, 2, status);

        double y = STATE_DIM.y() + STATE_DIM.height() + 2;
        for(ConsoleLog log : console.getLog()) {
            String msg = log.message().length() > 24 ? log.message().substring(0, 21) + "..." : log.message();
            Dim dimLog = new Dim(STATE_DIM.x(), y, STATE_DIM.width(), 2.5);
            g.drawTextLeft(dimLog, msg, Colors.TEXT);

            Dim dimLogState = new Dim(STATE_DIM.x() + 16, y, 4, 2.5);
            g.drawRoundRect(dimLogState, 2, log.event().getResult());

            y += 3;
        }
    }
}
