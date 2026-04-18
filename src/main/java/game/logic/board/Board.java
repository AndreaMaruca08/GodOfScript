package game.logic.board;

import game.logic.board.exceptions.*;
import game.logic.entity.enemies.Enemy;
import game.logic.entity.Entity;
import game.logic.entity.player.Player;
import game.logic.scripts.Event;
import game.logic.scripts.ScriptHelper;
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

    private double xpMultiplier;

    private Runnable onGameEnd;

    private Position playerPosition;
    private List<Enemy> enemies = new java.util.ArrayList<>(10);

    public Board(int width, int height, double xpMultiplier) {
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
        clean();
        this.xpMultiplier = xpMultiplier;
        playerPosition = new Position(0, 0);
    }
    public Board(int width, int height,  double xpMultiplier, boolean names) {
        this(width, height, xpMultiplier);
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

    public Tile getTile(Position position) {
        checkBounds(position.x(), position.y());
        return tiles[position.x()][position.y()];
    }

    public void setTile(int x, int y, Tile tile) {
        checkBounds(x, y);
        tiles[x][y] = tile;
    }

    public void setTile(Position pos, Tile tile) {
        checkBounds(pos.x(), pos.y());
        tiles[pos.x()][pos.y()] = tile;
    }

    public void move(Position start, Position end) {
        checkBounds(end.x(), end.y());
        checkBounds(start.x(), start.y());

        Tile startTile = tiles[start.x()][start.y()];
        Tile endTile = tiles[end.x()][end.y()];

        if(startTile.getEntity() == null)
            return;

        checkTile(endTile);

        Entity entity = startTile.getEntity();
        entity.setPosition(end);

        endTile.setEntity(entity);
        endTile.setOccupied(true);

        if(entity instanceof Player){
            playerPosition = end;
        }
    
        resetTile(startTile);
    }

    public void setupEnemy(Enemy enemy, Position position) {
        enemy.setPosition(position);
        setTile(position, new Tile(enemy, position));
    }

    public Player getPlayer(){
        return (Player) getTile(playerPosition).getEntity();
    }

    public void damageTo(Position position, double damage){
        checkBounds(position.x(), position.y());

        Entity entity = getEntityAt(position);
        if(entity == null)
            return;
        entity.takeDamage(damage);

        if(!entity.isDead()) return;

        if(entity instanceof Player p) throw new DeadPlayer(p);

        if(entity instanceof Enemy e) {
            getPlayer().gainXp(entity.toXp() * xpMultiplier);
            e.stopEnemy();
            enemies.removeIf(enemy -> enemy == e);
            if(noEnemies()) throw new DeadEnemies(getPlayer());
        }

        resetTile(getTile(position));
    }

    public void damageToPlayer(Position position, double damage){
        if(getEntityAt(position) != null && getEntityAt(position) instanceof Enemy)
            return;
        damageTo(position, damage);
    }

    public boolean noEnemies(){
        return enemies.isEmpty();
    }

    private Entity getEntityAt(Position position){
        return getTile(position).getEntity();
    }

    public Event aoeAction(Position center, int radius, boolean disruptWalls, Function<Position, Event> action) {
        boolean foundTarget = false;
        Event lastResult = Event.NO_ENEMY;

        for(int row = center.x() - radius; row < center.x() + radius*2; row++){
            for(int col = center.y() - radius; col < center.y() + radius*2; col++){
                if(row == center.x() && col == center.y()) continue;
                try {
                    if(disruptWalls && getTile(row, col).isWall()){
                        resetTile(getTile(row, col));
                        continue;
                    }
                    getTile(row, col).setExploded(true);
                    if(getTile(row, col).isEmpty()) continue;
                    foundTarget = true;
                    lastResult = action.apply(new Position(row, col));

                    if(lastResult == Event.WIN || lastResult == Event.DEAD) return lastResult;
                } catch (Exception _) {}
            }
        }

        return foundTarget ? lastResult : Event.NO_ENEMY;
    }

    public Event lineAction(Entity entity, String direction, int maxDistance, Function<Position, Event> action) {
        boolean foundTarget = false;
        Event lastResult = Event.NO_ENEMY;

        for(int i = 1; i <= maxDistance; i++) {
            Position currentPos = ScriptHelper.newDirectedPosition(direction, i, entity);
            Tile tile = getTile(currentPos);

            if(!isInBounds(currentPos) || tile.isWall()) {
                break;
            }
            tile.setTargeted(true);
            foundTarget = true;
            lastResult = action.apply(currentPos);

            if(lastResult == Event.WIN || lastResult == Event.DEAD) {
                return lastResult;
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

    public boolean isInBounds(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    public boolean isInBounds(Position p){
        return p.x() >= 0 && p.x() < width && p.y() >= 0 && p.y() < height;
    }

}
