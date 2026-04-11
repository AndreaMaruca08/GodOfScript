package game.logic.board;

import lombok.Data;

@Data
public class Board {
    private Tile[][] tiles;
    private int width;
    private int height;

    public Board(int width, int height) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
        clean();
    }

    public void clean(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                tiles[i][j] = Tile.empty();
            }
        }
    }

    public Tile getTile(int x, int y) {
        checkBounds(x, y);
        return tiles[x][y];
    }

    public void setTile(int x, int y, Tile tile) {
        checkBounds(x, y);
        tiles[x][y] = tile;
    }

    public void move(int x, int y) {
        checkBounds(x, y);
        if (tiles[x][y].isWall() || tiles[x][y].isOccupied()) {
            throw new IllegalStateException("Cannot move to occupied or wall tile");
        }
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new OutOfBoard(x, y);
        }
    }

}
