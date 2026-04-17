package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class RustDev extends Enemy {
    public RustDev(Ai ai) {
        super(180, 10, 18, "Rust Dev", ai);
    }
}