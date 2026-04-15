package game.logic.board;

public record Position (
        int x,
        int y
){
    public Position(Position position) {
        this(position.x, position.y);
    }

    public Position up() {
        return new Position(this.x, this.y - 1);
    }
    public Position down() {
        return new Position(this.x, this.y + 1);
    }
    public Position left() {
        return new Position(this.x - 1, this.y);
    }
    public Position right() {
        return new Position(this.x + 1, this.y);
    }

    public Position add(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }

    @Override
    public int hashCode() {
        return x + y;
    }
}
