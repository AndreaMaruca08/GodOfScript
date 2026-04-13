package game.logic.board.exceptions;

public class DeadPlayer extends BoardException {
    public DeadPlayer() {
        super("Player is dead");
    }
}
