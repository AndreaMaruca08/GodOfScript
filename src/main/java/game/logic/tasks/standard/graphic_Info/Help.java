package game.logic.tasks.standard.graphic_Info;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.tasks.Command;
import game.logic.tasks.Event;
import game.logic.tasks.Script;

import javax.swing.*;

public class Help extends Script {
    public Help() {
        super("help", Command.cmd("help", "show all available scripts"));
    }

    @Override
    public Event run(Entity player, Board board) throws Exception {
        StringBuilder message = new StringBuilder();
        message.append("Available commands:\n");
        for(Script script : player.getScripts()){
            message.append("  ").append(script.getHelp());
        }

        JOptionPane.showMessageDialog(null, message.toString());

        return Event.OK;
    }
}
