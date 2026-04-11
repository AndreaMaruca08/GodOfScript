package game.logic.tasks.standard;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.tasks.Event;
import game.logic.tasks.Command;
import game.logic.tasks.Task;

public class Up extends Task {

    public Up() {
        super("up",
                Command.cmd("up", "Move up"),
                Command.cmd("w", "Move up")
        );
    }

    @Override
    public Event run(Entity player, Board board) throws Exception {
        return null;
    }
}
