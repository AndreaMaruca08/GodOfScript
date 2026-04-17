package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class JuniorDev extends Enemy {
    public JuniorDev(Ai ai) {
        super(30, 3, 1, "Junior Dev", ai);
    }
}
