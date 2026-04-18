package game.logic.entity.enemies.ai;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.enemies.Enemy;
import game.logic.scripts.Event;
import game.logic.scripts.all.standard.damage.BaseAttack;
import game.logic.scripts.all.standard.movements.Down;
import game.logic.scripts.all.standard.movements.Left;
import game.logic.scripts.all.standard.movements.Right;
import game.logic.scripts.all.standard.movements.Up;
import lombok.Setter;

import javax.swing.*;

public abstract class Ai {
    protected final AIConfig config;
    @Setter
    protected Enemy body;
    protected Timer timer;
    protected final Board board;

    private boolean patrolDirection = true;
    private int moveCounter = 0;

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
        body.setMaxHp(body.getHp()*config.statsMultiplier);
        body.setHp(body.getMaxHp());
        body.setBaseAttack(body.getBaseAttack()*config.statsMultiplier);
        body.setBaseDefense(body.getBaseDefense()*config.statsMultiplier);
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

    protected void chasePlayer(Position enemyPos, Position playerPos) {
        if (playerPos.x() < enemyPos.x()) {
            if (!moveLeft()) {
                moveVertically(enemyPos, playerPos);
            }
        } else if (playerPos.x() > enemyPos.x()) {
            if (!moveRight()) {
                moveVertically(enemyPos, playerPos);
            }
        } else {
            moveVertically(enemyPos, playerPos);
        }
    }

    protected void moveVertically(Position enemyPos, Position playerPos) {
        if (playerPos.y() < enemyPos.y()) {
            moveUp();
        } else if (playerPos.y() > enemyPos.y()) {
            moveDown();
        }
    }

    protected void patrol() {
        moveCounter++;

        if (moveCounter < 4) {
            if (patrolDirection) {
                if (!moveLeft()) {
                    patrolDirection = false;
                    moveCounter = 0;
                }
            } else {
                if (!moveRight()) {
                    patrolDirection = true;
                    moveCounter = 0;
                }
            }
        } else {
            patrolDirection = !patrolDirection;
            moveCounter = 0;
        }
    }

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
