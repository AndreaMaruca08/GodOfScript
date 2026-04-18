package game.logic.scripts;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.console.Command;

public abstract class CooldownScript extends Script{
    double msCooldown;
    double lastRun;
    protected CooldownScript(String name, double msCooldown, Command... commands) {
        super(name, commands);
        this.msCooldown = msCooldown;
    }

    @Override
    public Event run(Entity entity, Board board){
        if(System.currentTimeMillis() - lastRun < msCooldown) return Event.COOLDOWN;
        Event res = internRun(entity, board);
        lastRun = System.currentTimeMillis();

        return res;
    }

    protected abstract Event internRun(Entity player, Board board);
}
