package game.logic.scripts.all.standard.graphic_Info;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.console.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;

public class ShowName extends Script {

    public ShowName() {
        super("show_name", Command.cmd("names", "show the name of the player"));
    }

    @Override
    public Event run(Entity entity, Board board) throws Exception {
        board.setNames(!board.isNames());
        return Event.OK;
    }
}
