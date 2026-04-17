package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class PhpDev extends Enemy {
    public PhpDev(Ai ai) {
        super(220, 10, 14, "PHP Dev", ai);
    }
}