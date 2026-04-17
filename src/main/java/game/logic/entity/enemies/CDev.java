package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class CDev extends Enemy {
    public CDev(Ai ai) {
        super(140, 12, 8, "C Dev", ai);
    }
}