package game.logic.scripts.all.level_based.lvl30;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.TimedScript;
import game.logic.scripts.console.Command;

public class DefenseBoost extends TimedScript {
    public DefenseBoost() {
        String desc = "increases defense for 20 seconds with a 30 seconds cooldown";
        super("Defense boost", 30000,20000,
                Command.cmd("def_boost", desc)
        );
    }
    @Override
    protected void singleRun(Entity entity, Board board) {
        entity.increaseDef(20.0);
    }

    @Override
    protected void whenEnds(Entity entity, Board board) {
        entity.decreaseDef(20.0);
    }
}
