package game.logic.scripts.standard.movements;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.exceptions.BoardException;
import game.logic.entity.Entity;
import game.logic.scripts.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;

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
