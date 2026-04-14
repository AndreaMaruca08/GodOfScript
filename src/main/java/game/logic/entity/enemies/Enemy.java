package game.logic.entity.enemies;

import game.logic.entity.Entity;
import game.logic.entity.enemies.ai.Ai;

public class Enemy extends Entity {
    public Ai ai;
    public Enemy(double maxHp, double baseAttack, double baseDefense, String name, Ai ai) {
        super(maxHp, baseAttack, baseDefense, name);
        setAi(ai);
        ai.setBody(this);
    }
    public Enemy(double maxHp, double baseAttack, double baseDefense, String name) {
        this(maxHp, baseAttack, baseDefense, name, null);
    }

    public void setAi(Ai ai){
        this.ai = ai;
        ai.setBody(this);
    }

    public void startEnemy(){
        ai.start();
    }
    public void stopEnemy(){
        ai.stop();
    }
}
