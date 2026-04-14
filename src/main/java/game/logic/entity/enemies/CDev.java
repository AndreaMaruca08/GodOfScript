package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class CDev extends Enemy {
    public CDev(Ai ai) {
        super(40, 10, 3, "C Dev", ai);
    }
}
