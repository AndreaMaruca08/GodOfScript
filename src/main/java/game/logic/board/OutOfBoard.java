package game.logic.board;

public class OutOfBoard extends RuntimeException {
    public OutOfBoard(int x, int y) {
        super("BOARD INDEX ERROR X: " + x + " Y: " + y );
    }
}
