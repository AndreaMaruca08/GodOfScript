package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class CDev extends Enemy {
    public CDev(Ai ai) {
        super(1000, 2, 3, "C Dev", ai);
    }
}
