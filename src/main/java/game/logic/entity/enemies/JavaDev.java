package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class JavaDev extends Enemy {
    public JavaDev(Ai ai) {
        super(150, 7, 10, "Java Dev", ai);
    }
}
