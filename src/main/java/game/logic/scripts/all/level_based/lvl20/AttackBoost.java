package game.logic.scripts.all.level_based.lvl20;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.TimedScript;
import game.logic.scripts.console.Command;

public class AttackBoost extends TimedScript {

    public AttackBoost() {
        String desc = "increases attack for 20 seconds with a 30 seconds cooldown";
        super("Attack boost", 30000,20000,
                Command.cmd("atk_boost", desc)
        );
    }

    @Override
    protected void singleRun(Entity entity, Board board) {
        entity.increaseAtk(30.0);
    }

    @Override
    protected void whenEnds(Entity entity, Board board) {
        entity.decreaseAtk(30.0);
    }
}
