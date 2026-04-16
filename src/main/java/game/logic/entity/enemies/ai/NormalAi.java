package game.logic.entity.enemies.ai;

import game.logic.board.Board;
import game.logic.board.Position;

public class NormalAi extends Ai{
    public NormalAi(Board board, AIConfig config) {
        super(board, config);
    }

    @Override
    protected void singleMove() {
        Position enemyPos = body.getPosition();
        Position playerPos = board.getPlayerPosition();

        int distance = Math.abs(enemyPos.x() - playerPos.x());

        if (distance < 3) {
            chasePlayer(enemyPos, playerPos);
        } else {
            patrol();
        }

        basicAttack();
    }
}
