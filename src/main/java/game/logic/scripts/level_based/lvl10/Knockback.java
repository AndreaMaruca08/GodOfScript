package game.logic.scripts.level_based.lvl10;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.*;

public class Knockback extends Script {
    public Knockback() {
        String desc = "Perform a knockback in every direction by 2 tile";
        super("knockback",
                Command.cmd("kn", desc, TypeOfParam.DIRECTION)
        );
    }
    @Override
    public Event run(Entity entity, Board board) throws Exception {
        board.aoeAction(entity.getPosition(), 1, false,
                targetPos ->{
                    if(board.getTile(targetPos).getEntity() != null) {
                        board.move(targetPos, ScriptHelper.newDirectedPosition(getArg(0), 3, entity));
                    }
                    return Event.OK;
                });
        return Event.OK;
    }
}
