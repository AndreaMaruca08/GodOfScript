package game.logic.tasks.standard.damage;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.Entity;
import game.logic.tasks.Command;
import game.logic.tasks.Event;
import game.logic.tasks.Script;

public class BaseAttack extends Script {
    public BaseAttack() {
        String desc = "Perform a basic attack around you";
        super("base attack",
                Command.cmd("base attack", desc),
                Command.cmd("attack", desc),
                Command.cmd("atk", desc)
        );
    }

    @Override
    public Event run(Entity entity, Board board) {
        Position pos = entity.getPosition();
        return board.aoeAction(pos, 1, targetPos -> {
            board.damageTo(targetPos, entity.getBaseAttack());
            return Event.OK;
        });
    }
}
