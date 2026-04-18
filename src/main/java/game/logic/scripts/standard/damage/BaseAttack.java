package game.logic.scripts.standard.damage;

import game.logic.scripts.ScriptHelper;
import game.logic.sound.Sounds;
import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.Entity;
import game.logic.scripts.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;

public class BaseAttack extends Script {
    boolean enemy = false;
    public BaseAttack() {
        String desc = "Perform a basic attack around you";
        super("base attack",
                Command.cmd("atk", desc),
                Command.cmd("base attack", desc),
                Command.cmd("attack", desc)
        );
    }
    public BaseAttack(boolean enemy) {
        this();
        this.enemy = enemy;
    }

    @Override
    public Event run(Entity entity, Board board) {
        Position pos = entity.getPosition();
        Sounds.attackSound();
        return board.aoeAction(pos, 1, false, targetPos -> ScriptHelper.catchWinAndLose(() -> {
            if(enemy) board.damageToPlayer(targetPos, entity.getBaseAttack());
            else board.damageTo(targetPos, entity.getBaseAttack());
        }));
    }
}
