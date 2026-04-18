package game.logic.scripts.level_based.lvl15;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.Command;
import game.logic.scripts.CooldownScript;
import game.logic.scripts.Event;
import game.logic.scripts.ScriptHelper;
import game.logic.sound.Sounds;

public class Explosion extends CooldownScript {

    public Explosion() {
        String desc = "Create an explosion 3 tiles around you";
        super("Explosion", 4000,
                Command.cmd("explosion", desc)
        );
    }

    @Override
    protected Event internRun(Entity player, Board board) {
        Sounds.explosionSound();
        return board.aoeAction(player.getPosition(), 3, true, pos ->
                ScriptHelper.catchWinAndLose(() -> board.damageTo(pos, player.getBaseAttack()*3))
        );
    }
}
