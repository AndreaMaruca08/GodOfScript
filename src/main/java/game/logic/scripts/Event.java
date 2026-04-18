package game.logic.scripts;

import game.graphic.shared.Colors;
import lombok.Getter;

import java.awt.*;

@Getter
public enum Event {
    DEAD(Colors.ERROR),
    OK(Colors.SUCCESS),
    OCCUPIED(Colors.INFO),
    NO_ENEMY(Colors.NO_ENEMY),
    ERROR(Colors.ERROR),
    NOT_FOUND(Colors.WARNING),
    COOLDOWN(Colors.NO_ENEMY),

    WIN(Colors.WIN);

    private final Color result;

    Event(Color result) {
        this.result = result;
    }
}
