package game.logic.board.exceptions;

public class OutOfBoard extends BoardException {
    public OutOfBoard(int x, int y) {
        super("BOARD INDEX ERROR X: " + x + " Y: " + y );
    }
}
