package game.logic.scripts;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.console.Command;

import javax.swing.*;

public abstract class TimedScript extends Script{
    int msCooldown;
    double lastRun;
    int msExpiration;

    protected TimedScript(String name, int msCooldown, int msExpiration, Command... commands) {
        super(name, commands);
        this.msCooldown = msCooldown;
        this.msExpiration = msExpiration;
    }

    @Override
    public Event run(Entity entity, Board board) throws Exception {
        if(System.currentTimeMillis() - lastRun < msCooldown) return Event.COOLDOWN;
        Timer timer = new Timer(msExpiration, _ -> whenEnds(entity, board));
        timer.setRepeats(false);

        singleRun(entity, board);
        timer.start();

        lastRun = System.currentTimeMillis();

        return Event.OK;
    }

    protected abstract void singleRun(Entity entity, Board board);
    protected abstract void whenEnds(Entity entity, Board board);
}
