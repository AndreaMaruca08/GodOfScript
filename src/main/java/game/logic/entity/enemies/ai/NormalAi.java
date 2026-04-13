package game.logic.entity.enemies.ai;

import game.logic.board.Board;

public class NormalAi extends Ai{
    public NormalAi(Board board, AIConfig config) {
        super(board, config);
    }

    private boolean muro = false;

    @Override
    protected void singleMove() {
        System.out.println(body.getPosition() + "   INIZIO");
        System.out.println("  muro=" + muro + " basicAttack=" + basicAttack());

        if(muro) {
            boolean moved = moveRight();
            System.out.println("  moveRight() = " + moved + " -> " + body.getPosition());
            muro = !moved;
        } else {
            boolean moved = moveLeft();
            System.out.println("  moveLeft() = " + moved + " -> " + body.getPosition());
            if(!moved) {
                muro = true;
            }
            basicAttack();
        }

        System.out.println(body.getPosition() + "   FINE");
    }
}
