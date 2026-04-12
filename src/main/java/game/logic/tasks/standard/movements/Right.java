package game.logic.tasks.standard.movements;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.exceptions.BoardException;
import game.logic.entity.Entity;
import game.logic.tasks.Command;
import game.logic.tasks.Event;
import game.logic.tasks.Script;

public class Right extends Script {
    public Right() {
        super("right",
                Command.cmd("right", "Move right"),
                Command.cmd("d", "Move right")
        );
    }

    @Override
    public Event run(Entity entity, Board board) {
        Position entityPosition = entity.getPosition();
        Position newPosition = entityPosition.right();

        try {
            board.move(entityPosition, newPosition);
        } catch (BoardException e) {
            return Event.OCCUPIED;
        }

        return Event.OK;
    }
}
