package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class CppDev extends Enemy {
    public CppDev(Ai ai) {
        super(160, 26, 10, "C++ Dev", ai);
    }
}