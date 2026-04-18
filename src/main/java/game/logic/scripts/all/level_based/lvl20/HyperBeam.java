package game.logic.scripts.all.level_based.lvl20;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.*;
import game.logic.scripts.console.Command;
import game.logic.scripts.console.TypeOfParam;
import game.logic.sound.Sounds;

public class HyperBeam extends CooldownScript {
    public HyperBeam() {
        String desc = "Creates a hyper beam in a direction, does 4 times the normal damage and pierces through walls";
        super("HyperBeam", 5000,
                Command.cmd("hyper", desc, TypeOfParam.DIRECTION),
                Command.cmd("hyperBeam", desc, TypeOfParam.DIRECTION));
    }

    @Override
    protected Event internRun(Entity entity, Board board) {
        if(getArg(0) == null) return Event.NO_ENEMY;
        Sounds.hyperBeamSound();
        return board.lineAction(entity, getArg(0), 10, true,
                endBeamPosition -> ScriptHelper.catchWinAndLose(() ->
                        board.damageTo(endBeamPosition, entity.getBaseAttack() * 4)
                )
        );
    }
}
