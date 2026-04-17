package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class PythonDev extends Enemy {
    public PythonDev(Ai ai) {
        super(100, 14, 4, "Python Dev", ai);
    }
}