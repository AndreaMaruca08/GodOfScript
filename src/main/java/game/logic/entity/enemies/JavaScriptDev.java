package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class JavaScriptDev extends Enemy {
    public JavaScriptDev(Ai ai) {
        super(60, 8, 1, "JS Dev", ai);
    }
}