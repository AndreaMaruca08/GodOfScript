package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class JavaDev extends Enemy {
    public JavaDev(Ai ai) {
        super(170, 16, 12, "Java Dev", ai);
    }
}