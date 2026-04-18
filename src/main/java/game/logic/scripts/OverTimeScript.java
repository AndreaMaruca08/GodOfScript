package game.logic.scripts;

import game.logic.board.Board;
import game.logic.entity.Entity;
import game.logic.scripts.console.Command;

import javax.swing.*;

public abstract class OverTimeScript extends Script{
    int msCooldown;
    double lastRun;
    int repetition;

    protected OverTimeScript(String name, int msCooldown, int repetition, Command... commands) {
        super(name, commands);
        this.msCooldown = msCooldown;
        this.repetition = repetition;
    }

    @Override
    public Event run(Entity entity, Board board){
        if(System.currentTimeMillis() - lastRun < msCooldown) return Event.COOLDOWN;

        int[] counter = {0};
        int maxRepetitions = repetition;

        Timer timer = new Timer(500, e -> {
            singleTick(entity, board);

            if (counter[0] >= maxRepetitions) {
                ((Timer) e.getSource()).stop();
            }
            counter[0]++;
        });

        timer.start();

        lastRun = System.currentTimeMillis();

        return Event.OK;
    }

    protected abstract void singleTick(Entity player, Board board);
}
