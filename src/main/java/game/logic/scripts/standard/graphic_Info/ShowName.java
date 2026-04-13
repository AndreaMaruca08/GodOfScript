package game.logic.scripts.standard.graphic_Info;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;

public class ShowName extends Script {

    public ShowName() {
        super("show_name", Command.cmd("names", "show the name of the player"));
    }

    @Override
    public Event run(Entity player, Board board) throws Exception {
        board.setNames(!board.isNames());
        return Event.OK;
    }
}
