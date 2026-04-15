package game.logic.scripts.level_based.lvl10;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;
import game.logic.scripts.TypeOfParam;
import game.logic.scripts.standard.movements.Jump;

public class Knockback extends Script {
    public Knockback() {
        String desc = "Perform a knockback in every direction by 2 tile";
        super("knockback",
                Command.cmd("kn", desc, TypeOfParam.DIRECTION)
        );
    }
    @Override
    public Event run(Entity entity, Board board) throws Exception {
        board.aoeAction(entity.getPosition(), 1,
                targetPos ->{
                    if(board.getTile(targetPos).getEntity() != null) {
                        board.move(targetPos, Jump.newPosition(getArg(0), 2, entity));
                    }
                    return Event.OK;
                });
        return Event.OK;
    }
}
