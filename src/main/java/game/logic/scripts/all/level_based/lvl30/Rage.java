package game.logic.scripts.all.level_based.lvl30;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.TimedScript;
import game.logic.scripts.console.Command;

public class Rage extends TimedScript {
    public Rage() {
        String desc = "Rages for 8 seconds, 30% stats, but you will get tired after";
        super("Rage", 30000,7000,
                Command.cmd("rage", desc)
        );
    }
    @Override
    protected void singleRun(Entity entity, Board board) {
        entity.increaseAtk(30);
        entity.increaseDef(30);
        entity.lose(15);
        entity.setEnraged(true);
    }

    @Override
    protected void whenEnds(Entity entity, Board board) {
        entity.decreaseAtk(50);
        entity.decreaseDef(50);
        entity.setEnraged(false);
    }
}
