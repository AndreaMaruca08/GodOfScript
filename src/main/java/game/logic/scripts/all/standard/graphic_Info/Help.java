package game.logic.scripts.all.standard.graphic_Info;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.console.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;

import javax.swing.*;

public class Help extends Script {
    private boolean dialogShowing = false;

    public Help() {
        super("help", Command.cmd("help", "show all available scripts"));
    }

    @Override
    public Event run(Entity entity, Board board) throws Exception {
        if(dialogShowing) return Event.OK;

        dialogShowing = true;

        StringBuilder message = new StringBuilder();
        message.append("Available commands:\n");

        entity.getScripts().stream()
                .distinct()
                .forEach(script -> message.append("  ").append(script.getHelp()));

        JOptionPane.showMessageDialog(null, message.toString());

        dialogShowing = false;
        return Event.OK;
    }
}
