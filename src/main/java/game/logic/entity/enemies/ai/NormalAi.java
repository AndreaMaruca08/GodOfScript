package game.logic.entity.enemies.ai;

import game.logic.board.Board;

public class NormalAi extends Ai{
    public NormalAi(Board board, AIConfig config) {
        super(board, config);
    }

    private boolean muro = false;

    @Override
    protected void singleMove() {

        if(muro) {
            muro = !moveRight();
        } else {
            if(!moveLeft()) {
                muro = true;
            }
            basicAttack();
        }
    }
}
