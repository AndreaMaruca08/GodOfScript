package game.logic.tasks.standard.movements;

import game.logic.board.Board;
import game.logic.board.exceptions.BoardException;
import game.logic.board.Position;
import game.logic.entity.Entity;
import game.logic.tasks.Event;
import game.logic.tasks.Command;
import game.logic.tasks.Script;

public class Up extends Script {

    public Up() {
        super("up",
                Command.cmd("up", "Move up"),
                Command.cmd("w", "Move up")
        );
    }

    @Override
    public Event run(Entity entity, Board board) {
        Position entityPosition = entity.getPosition();
        Position newPosition = entityPosition.up();

        try {
            board.move(entityPosition, newPosition);
        } catch (BoardException e) {
            return Event.OCCUPIED;
        }

        return Event.OK;
    }
}
