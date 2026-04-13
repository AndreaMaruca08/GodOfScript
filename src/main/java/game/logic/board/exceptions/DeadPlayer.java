package game.logic.board.exceptions;

import game.logic.entity.Player;
import lombok.Getter;

public class DeadPlayer extends BoardException {
    @Getter
    private final Player player;
    public DeadPlayer(Player player) {
        super("Player is dead");
        this.player = player;
    }
}
