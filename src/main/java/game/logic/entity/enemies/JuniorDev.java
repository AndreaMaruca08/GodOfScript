package game.logic.entity.enemies;

import game.logic.entity.Enemy;
import game.logic.entity.enemies.ai.Ai;

public class JuniorDev extends Enemy {
    public JuniorDev(Ai ai) {
        super(20, 2, 1, "Junior Dev", ai);
    }
}
