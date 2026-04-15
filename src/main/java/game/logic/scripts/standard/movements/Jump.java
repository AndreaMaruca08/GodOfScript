package game.logic.scripts.standard.movements;

import game.logic.board.Board;
import game.logic.board.Position;
import game.logic.entity.Entity;
import game.logic.scripts.Command;
import game.logic.scripts.Event;
import game.logic.scripts.Script;
import game.logic.scripts.TypeOfParam;

public class Jump extends Script {

    public Jump() {
        super("Jump",
                Command.cmd(
                        "jump", "Jump from current position to the next one",
                        TypeOfParam.STRING, TypeOfParam.INT
                )
        );
    }

    @Override
    public Event run(Entity player, Board board) {
        String direction = getArg(0);
        int distance = getIntArg(1);

        Position position = player.getPosition();
        Position newPosition = newPosition(direction, distance, player);

        try {
            board.move(position, newPosition);
        } catch (Exception e) {
            return Event.OCCUPIED;
        }

        return Event.OK;
    }

    public static Position newPosition(String direction, int distance, Entity player) {
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
