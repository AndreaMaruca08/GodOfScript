package game.logic.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TypeOfUpgrade {

    HP {
        @Override
        public void upgrade(Entity entity) {
            entity.setMaxHp(entity.getMaxHp() * 1.06);
            entity.setHp(entity.getMaxHp());
            entity.points--;
        }
    },
    ATTACK {
        @Override
        public void upgrade(Entity entity) {
            entity.setBaseAttack(entity.getBaseAttack() * 1.06);
            entity.points--;
        }
    },
    DEFENSE {
        @Override
        public void upgrade(Entity entity) {
            entity.setBaseDefense(entity.getBaseDefense() * 1.04);
            entity.points--;
        }
    };

    public abstract void upgrade(Entity entity);
}
