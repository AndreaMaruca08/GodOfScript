import game.logic.entity.Entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    @Test
    void constructorInitializesFields() {
        Entity e = new Entity(50, 10, 5, "Hero");

        assertEquals("Hero", e.getName());
        assertEquals(50, e.getMaxHp());
        assertEquals(50, e.getHp());
        assertEquals(10, e.getBaseAttack());
        assertEquals(10, e.getMaxBaseAttack());
        assertEquals(5, e.getBaseDefense());
        assertEquals(5, e.getMaxBaseDefense());
        assertEquals(1, e.getLevel());
        assertEquals(0, e.getPoints());
        assertFalse(e.isEnraged());
        assertFalse(e.isDead());
    }

    @Test
    void nullNameReplacedByDefault() {
        Entity e = new Entity(10, 1, 1, null);
        assertEquals("No name", e.getName());
    }

    @Test
    void negativeStatsAreClampedToZero() {
        Entity e = new Entity(-10, -5, -3, "Bad");
        assertEquals(0, e.getMaxHp());
        assertEquals(0, e.getBaseAttack());
        assertEquals(0, e.getBaseDefense());
    }

    @Test
    void takeDamageAppliesDefense() {
        Entity e = new Entity(100, 0, 10, "E");
        e.takeDamage(30);

        assertEquals(80, e.getHp(), 0.0001);
    }

    @Test
    void takeDamageLessThanDefenseDoesNothing() {
        Entity e = new Entity(100, 0, 10, "E");
        e.takeDamage(5);

        assertEquals(100, e.getHp(), 0.0001);
    }

    @Test
    void isDeadWhenHpDropsToZero() {
        Entity e = new Entity(10, 0, 0, "E");
        e.takeDamage(10);
        assertTrue(e.isDead());
    }

    @Test
    void regenerateAddsPercentageOfMaxHp() {
        Entity e = new Entity(100, 0, 0, "E");
        e.takeDamage(50);
        e.regenerate(20);

        assertEquals(70, e.getHp(), 0.0001);
    }

    @Test
    void loseDecreasesHpByPercentage() {
        Entity e = new Entity(100, 0, 0, "E");
        e.lose(25);
        assertEquals(75, e.getHp(), 0.0001);
    }

    @Test
    void increaseAtkByAmount() {
        Entity e = new Entity(100, 10, 0, "E");
        e.increaseAtk(5.0);
        assertEquals(15, e.getBaseAttack(), 0.0001);
    }

    @Test
    void increaseAtkByPercentage() {
        Entity e = new Entity(100, 10, 0, "E");
        e.increaseAtk(50);
        assertEquals(15, e.getBaseAttack(), 0.0001);
    }

    @Test
    void decreaseAtkByAmount() {
        Entity e = new Entity(100, 10, 0, "E");
        e.decreaseAtk(3.0);
        assertEquals(7, e.getBaseAttack(), 0.0001);
    }

    @Test
    void decreaseAtkByPercentage() {
        Entity e = new Entity(100, 10, 0, "E");
        e.decreaseAtk(50);
        assertEquals(5, e.getBaseAttack(), 0.0001);
    }

    @Test
    void increaseDefByAmount() {
        Entity e = new Entity(100, 0, 5, "E");
        e.increaseDef(10.0);
        assertEquals(15, e.getBaseDefense(), 0.0001);
    }

    @Test
    void decreaseDefByPercentage() {
        Entity e = new Entity(100, 0, 10, "E");
        e.decreaseDef(50);
        assertEquals(5, e.getBaseDefense(), 0.0001);
    }

    @Test
    void hpPercentageComputedCorrectly() {
        Entity e = new Entity(100, 0, 0, "E");
        e.takeDamage(40);
        assertEquals(0.6, e.getHpPercentage(), 0.0001);
    }

    @Test
    void toXpCombinesStats() {
        Entity e = new Entity(100, 10, 5, "E");
        assertEquals(100 + 10 * 3 + 5 * 4, e.toXp(), 0.0001);
    }

    @Test
    void calcXpNeededIncreasesWithLevel() {
        Entity e1 = new Entity(100, 1, 1, "E");
        double level1 = e1.calcXpNeeded();

        e1.setLevel(5);
        double level5 = e1.calcXpNeeded();

        assertTrue(level5 > level1);
    }

    @Test
    void positionDefaultsToMinusOne() {
        Entity e = new Entity(10, 1, 1, "E");
        assertEquals(-1, e.getPosition().x());
        assertEquals(-1, e.getPosition().y());
    }

    @Test
    void getColorChangesByHp() {
        Entity e = new Entity(100, 0, 0, "E");
        assertNotNull(e.getColor());
        e.takeDamage(50);
        assertNotNull(e.getColor());
        e.takeDamage(30);
        assertNotNull(e.getColor());
    }
}