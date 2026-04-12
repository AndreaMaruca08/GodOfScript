package game.logic.tasks.standard.movements;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.exceptions.BoardException;
import game.logic.entity.Entity;
import game.logic.tasks.Command;
import game.logic.tasks.Event;
import game.logic.tasks.Script;

public class Down extends Script {
    public Down() {
        super("down",
                Command.cmd("down", "Move down"),
                Command.cmd("s", "Move down")
        );
    }

    @Override
    public Event run(Entity entity, Board board) {
        Position entityPosition = entity.getPosition();
        Position newPosition = entityPosition.down();

        try {
            board.move(entityPosition, newPosition);
        } catch (BoardException e) {
            return Event.OCCUPIED;
        }

        return Event.OK;
    }
}
