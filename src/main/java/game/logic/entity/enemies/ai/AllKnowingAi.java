package game.logic.entity.enemies.ai;

import game.logic.board.Board;

public class AllKnowingAi extends NormalAi{
    public AllKnowingAi(Board board, AIConfig config) {
        super(board, config);
        this.range = 1000;
    }
}
