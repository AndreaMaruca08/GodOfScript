package game.logic.tasks.standard.movements;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.exceptions.BoardException;
import game.logic.entity.Entity;
import game.logic.tasks.Command;
import game.logic.tasks.Event;
import game.logic.tasks.Script;

public class Left extends Script {
    public Left() {
        super("left",
                Command.cmd("left", "Move left"),
                Command.cmd("a", "Move left")
        );
    }

    @Override
    public Event run(Entity entity, Board board) {
        Position entityPosition = entity.getPosition();
        Position newPosition = entityPosition.left();

        try {
            board.move(entityPosition, newPosition);
        } catch (BoardException e) {
            return Event.OCCUPIED;
        }

        return Event.OK;
    }
}
