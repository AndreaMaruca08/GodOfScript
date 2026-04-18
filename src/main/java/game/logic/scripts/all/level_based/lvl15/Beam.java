package game.logic.scripts.all.level_based.lvl15;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.*;
import game.logic.scripts.console.Command;
import game.logic.scripts.console.TypeOfParam;

public class Beam extends Script {
    public Beam() {
        String desc = "Perform a beam with a 3 tile range in a direction, does half the normal damage";
        super("Beam",
                Command.cmd("be", desc, TypeOfParam.DIRECTION),
                Command.cmd("beam", desc, TypeOfParam.DIRECTION)
        );
    }
    @Override
    public Event run(Entity entity, Board board) throws Exception {
        return board.lineAction(entity, getArg(0), 4, false, endBeamPosition ->
                ScriptHelper.catchWinAndLose(() ->
                        board.damageTo(endBeamPosition, entity.getBaseAttack() / 2)
                )
        );
    }
}
