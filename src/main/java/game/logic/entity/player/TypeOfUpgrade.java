package game.logic.entity.player;

import game.logic.entity.Entity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeOfUpgrade {
    HP {
        @Override
        public void upgrade(Entity entity) {
            entity.setMaxHp(entity.getMaxHp() * 1.08);
            entity.setHp(entity.getMaxHp());
            entity.setPoints(entity.getPoints() - 1);
        }
    },
    ATTACK {
        @Override
        public void upgrade(Entity entity) {
            entity.setBaseAttack(entity.getBaseAttack() * 1.08);
            entity.setPoints(entity.getPoints() - 1);
        }
    },
    DEFENSE {
        @Override
        public void upgrade(Entity entity) {
            entity.setBaseDefense(entity.getBaseDefense() * 1.07);
            entity.setPoints(entity.getPoints() - 1);
        }
    };

    public abstract void upgrade(Entity entity);
}
