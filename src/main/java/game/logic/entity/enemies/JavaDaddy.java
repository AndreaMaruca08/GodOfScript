package game.logic.entity.enemies;

import game.logic.entity.enemies.ai.Ai;

public class JavaDaddy extends Enemy {
    public JavaDaddy(Ai ai) {
        super(600, 50, 60, "Java Daddy", ai);
    }
}