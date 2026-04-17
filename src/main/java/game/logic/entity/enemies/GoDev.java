package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class GoDev extends Enemy {
    public GoDev(Ai ai) {
        super(120, 20, 6, "Go Dev", ai);
    }
}