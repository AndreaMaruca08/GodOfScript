package game.logic.board;

import game.logic.board.exceptions.*;
import game.logic.entity.Enemy;
import game.logic.entity.Entity;
import game.logic.entity.Player;
import game.logic.scripts.Event;
import lombok.Data;

import java.util.List;
import java.util.function.Function;

@Data
public class Board {
    private Tile[][] tiles;
    private int width;
    private int height;

    private boolean active = false;
    private boolean names = true;

    private Position playerPosition;
    private List<Enemy> enemies = new java.util.ArrayList<>(10);

    public Board(int width, int height) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
        clean();
        playerPosition = new Position(0, 0);
    }
    public Board(int width, int height, boolean names) {
        this(width, height);
        this.names = names;
    }

    public void searchEnemies(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(tiles[i][j].getEntity() instanceof Enemy e){
                    enemies.add(e);
                }
            }
        }
    }

    private int searchAliveEnemies(){
        return enemies.stream()
                .filter(enemy ->!enemy.isDead())
                .toList().size();
    }

    public void start(){
        active = true;
        for(Enemy enemy : enemies){
            enemy.startEnemy();
        }
    }
    public void stop(){
        active = false;
        for(Enemy enemy : enemies){
            enemy.stopEnemy();
        }
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

    public void move(Position start, Position end) {
        checkBounds(end.x(), end.y());
        checkBounds(start.x(), start.y());

        Tile startTile = tiles[start.x()][start.y()];
        Tile endTile = tiles[end.x()][end.y()];

        checkTile(endTile);

        Entity entity = startTile.getEntity();
        if(entity != null) {
            entity.setPosition(end);
        
            endTile.setEntity(entity);
            endTile.setOccupied(true);
        
        }

        if(entity instanceof Player){
            playerPosition = end;
        }
    
        resetTile(startTile);
    }

    public void setupEnemy(Enemy enemy, Position position) {
        enemy.setPosition(position);
        setTile(position.x(), position.y(), new Tile(enemy, position));
    }

    public Player getPlayer(){
        return (Player) getTile(playerPosition.x(), playerPosition.y()).getEntity();
    }

    public void damageTo(Position position, double damage){
        checkBounds(position.x(), position.y());
        Tile tile = tiles[position.x()][position.y()];
        Entity entity = tile.getEntity();
        if(entity != null){
            entity.takeDamage(damage);
            if(!entity.isDead()) return;

            if(entity instanceof Player) throw new DeadPlayer(getPlayer());

            resetTile(tile);
            getPlayer().gainXp(entity.toXp());
            if(entity instanceof Enemy e) {
                e.stopEnemy();
                enemies.remove(e);
            }
            if(noEnemies()) throw new DeadEnemies(getPlayer());
        }
    }

    public boolean noEnemies(){
        return searchAliveEnemies() == 0;
    }

    public Event aoeAction(Position centerPos, int radius, Function<Position, Event> action) {
        boolean foundTarget = false;
        Event lastResult = Event.NO_ENEMY;

        for(int row = centerPos.x() - radius; row < centerPos.x() + radius*2; row++){
            for(int col = centerPos.y() - radius; col < centerPos.y() + radius*2; col++){
                if(row == centerPos.x() && col == centerPos.y()) continue;
                try {
                    if(getTile(row, col).isEmpty()) continue;

                    foundTarget = true;
                    lastResult = action.apply(new Position(row, col));
                } catch (Exception _) {}
            }
        }

        return foundTarget ? lastResult : Event.NO_ENEMY;
    }

    public void setupPlayer(Player player, Position position) {
        player.setPosition(position);
        setPlayerPosition(position);
        setTile(position.x(), position.y(), new Tile(player, position));
    }

    private void resetTile(Tile tile){
        tile.setEntity(null);
        tile.setOccupied(false);
        tile.setWall(false);
    }

    private void checkTile(Tile tile){
        if (tile.isWall()) {
            throw new WallException("Cannot move to wall tile");
        }
        if (tile.isOccupied()) {
            throw new OccupiedException("Cannot move to occupied tile");
        }
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new OutOfBoard(x, y);
        }
    }

}
