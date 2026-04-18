import game.logic.entity.enemies.Enemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    void enemyConstructorSetsFields() {
        Enemy e = new Enemy(50, 5, 2, "Goblin");

        assertEquals("Goblin", e.getName());
        assertEquals(50, e.getMaxHp());
        assertEquals(5, e.getBaseAttack());
        assertEquals(2, e.getBaseDefense());
        assertNull(e.ai);
    }

    @Test
    void enemyConstructorWithNullAiDoesNotThrow() {
        assertDoesNotThrow(() -> new Enemy(10, 1, 1, "X", null));
    }

    @Test
    void setAiWithNullIsSafe() {
        Enemy e = new Enemy(10, 1, 1, "X");
        assertDoesNotThrow(() -> e.setAi(null));
        assertNull(e.ai);
    }
}