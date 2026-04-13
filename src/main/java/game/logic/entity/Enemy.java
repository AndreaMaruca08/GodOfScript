package game.logic.entity;

import game.logic.entity.enemies.ai.Ai;
import lombok.Setter;

@Setter
public class Enemy extends Entity {
    protected Ai ai;
    public Enemy(double maxHp, double baseAttack, double baseDefense, String name, Ai ai) {
        super(maxHp, baseAttack, baseDefense, name);
        setAi(ai);
        ai.setBody(this);
    }

    public void startEnemy(){
        ai.start();
    }
    public void stopEnemy(){
        ai.stop();
    }
}
