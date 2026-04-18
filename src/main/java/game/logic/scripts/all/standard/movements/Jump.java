package game.logic.scripts.all.standard.movements;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.Entity;
import game.logic.scripts.*;
import game.logic.scripts.console.Command;
import game.logic.scripts.console.TypeOfParam;

public class Jump extends Script {

    public Jump() {
        super("Jump",
                Command.cmd(
                        "jump", "Jump from current position to the next one",
                        TypeOfParam.STRING, TypeOfParam.INT
                )
        );
    }

    @Override
    public Event run(Entity entity, Board board) {
        String direction = getArg(0);
        int distance = getIntArg(1);

        Position position = entity.getPosition();
        Position newPosition = ScriptHelper.newDirectedPosition(direction, distance, entity);

        try {
            board.move(position, newPosition);
        } catch (Exception e) {
            return Event.OCCUPIED;
        }

        return Event.OK;
    }


}
