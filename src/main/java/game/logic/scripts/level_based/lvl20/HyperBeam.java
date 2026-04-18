package game.logic.scripts.level_based.lvl20;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.*;
import game.logic.sound.Sounds;

public class HyperBeam extends CooldownScript {
    public HyperBeam() {
        super("HyperBeam", 5000,
                Command.cmd("hyper", "Creates a hyper beam", TypeOfParam.DIRECTION));
    }

    @Override
    protected Event internRun(Entity entity, Board board) {
        Sounds.hyperBeamSound();
        return board.lineAction(entity, getArg(0), 10, endBeamPosition -> ScriptHelper.catchWinAndLose(
                () -> board.damageTo(endBeamPosition, entity.getBaseAttack() * 4))
        );
    }
}
