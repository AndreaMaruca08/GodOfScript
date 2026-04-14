package game.logic.board.exceptions;

import game.logic.entity.player.Player;
import lombok.Getter;

public class DeadEnemies extends BoardException {
    @Getter
    private final Player player;
    public DeadEnemies(Player player) {
        super("Win");
        this.player = player;
    }
}
