package game.logic.scripts;

import game.logic.board.Position;
import game.logic.board.exceptions.DeadEnemies;
import game.logic.board.exceptions.DeadPlayer;
import game.logic.entity.Entity;
import game.logic.entity.player.DataSaver;

public final class ScriptHelper {
    private ScriptHelper() {}

    public static Event catchWinAndLose(Runnable actionToTry){
        try {
           actionToTry.run();
        }catch (DeadPlayer d){
            d.getPlayer().reset();
            DataSaver.savePlayer(d.getPlayer());
            return Event.DEAD;
        }catch (DeadEnemies d){
            d.getPlayer().reset();
            DataSaver.savePlayer(d.getPlayer());
            return Event.WIN;
        }
        return Event.OK;
    }

    public static Position newDirectedPosition(String direction, int distance, Entity player) {
        Position playerPosition = player.getPosition();

        if(distance < 0 || distance > 7) throw new IllegalArgumentException("Distanza non valida: " + distance);

        return switch (direction.toLowerCase()) {
            case "up", "w" -> playerPosition.add(0, -distance);
            case "down", "s" -> playerPosition.add(0, distance);
            case "left", "a" -> playerPosition.add(-distance, 0);
            case "right", "d" -> playerPosition.add(distance, 0);
            default -> throw new IllegalArgumentException("Direzione non valida: " + direction);
        };
    }
}

