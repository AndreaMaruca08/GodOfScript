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
        }
    },
    ATTACK {
        @Override
        public void upgrade(Entity entity) {
            entity.setMaxBaseAttack(entity.getMaxBaseAttack() * 1.08);
            entity.setBaseAttack(entity.getMaxBaseAttack());
        }
    },
    DEFENSE {
        @Override
        public void upgrade(Entity entity) {
            entity.setMaxBaseDefense(entity.getMaxBaseDefense() * 1.05);
            entity.setBaseDefense(entity.getMaxBaseDefense());
        }
    };

    public abstract void upgrade(Entity entity);
}
