package game.logic.entity.enemies;

import game.logic.entity.Enemy;
import game.logic.entity.enemies.ai.Ai;

public class JavaDaddy extends Enemy {
    public JavaDaddy(Ai ai) {
        super(400, 30, 40, "Java Daddy", ai);
    }
}
