package game.logic.entity.enemies.ai;

import game.logic.board.Board;
import game.logic.entity.enemies.Enemy;
import game.logic.scripts.Event;
import game.logic.scripts.standard.damage.BaseAttack;
import game.logic.scripts.standard.movements.Down;
import game.logic.scripts.standard.movements.Left;
import game.logic.scripts.standard.movements.Right;
import game.logic.scripts.standard.movements.Up;
import lombok.Setter;

import javax.swing.*;

public abstract class Ai {
    protected final AIConfig config;
    @Setter
    protected Enemy body;
    protected Timer timer;
    protected final Board board;

    public Ai(Board board,  AIConfig config) {
        this.config = config;
        this.board = board;
        timer = new Timer(config.delayMs, _ -> singleMove());
        timer.setInitialDelay(config.initialDelayMs);
    }

    public double getXpMultiplier() {
        return config.xpMultiplier;
    }

    public void start() {
        timer.start();
    }
    public void stop() {
        timer.stop();
    }

    protected abstract void singleMove();

    private static final BaseAttack atk = new BaseAttack(true);
    private static final Left left = new Left();
    private static final Right right = new Right();
    private static final Up up = new Up();
    private static final Down down = new Down();

    protected final boolean basicAttack(){
        Event result = atk.run(body, board);
        if (result == Event.DEAD) {
            board.stop();
            JOptionPane.showMessageDialog(null, "You died!");
            if (board.getOnGameEnd() != null) board.getOnGameEnd().run();
        } else if (result == Event.WIN) {
            board.stop();
            JOptionPane.showMessageDialog(null, "You win!");
            if (board.getOnGameEnd() != null) board.getOnGameEnd().run();
        }
        return result == Event.OK;
    }

    protected final boolean moveUp(){
        return up.run(body, board) == Event.OK;
    }

    protected final boolean moveDown(){
        return down.run(body, board) == Event.OK;
    }

    protected final boolean moveLeft(){
        return left.run(body, board) == Event.OK;
    }

    protected final boolean moveRight(){
        return right.run(body, board) == Event.OK;
    }
}
