package game.logic.scripts.level_based.lvl10;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.board.exceptions.BoardException;
import game.logic.entity.Entity;
import game.logic.scripts.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;
import game.logic.scripts.TypeOfParam;
import game.logic.scripts.standard.movements.Jump;

public class Dash extends Script {
    public Dash() {
        String desc = "Perform a Dash towards a direction";
        super("Dash",
                Command.cmd("dash", desc, TypeOfParam.DIRECTION)
        );
    }
    @Override
    public Event run(Entity entity, Board board) throws Exception {
        Position playerPosition = entity.getPosition();
        Position newPosition = Jump.newPosition(getArg(0), 3, entity);

        try {
            board.move(playerPosition, newPosition);
        }
        catch (BoardException e) {
            return Event.OCCUPIED;
        }

        return Event.OK;
    }

}
