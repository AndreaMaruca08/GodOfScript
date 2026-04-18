package game.logic.scripts.all.level_based.lvl20;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.OverTimeScript;
import game.logic.scripts.console.Command;

public class Regen extends OverTimeScript {
    public Regen() {
        super("Regeneration", 20000, 5,
                Command.cmd("regeneration", "Regenerate health")
        );
    }

    @Override
    protected void singleTick(Entity entity, Board board) {
        entity.regenerate(4);
    }
}
