import game.logic.entity.player.Player;
import game.logic.entity.player.TypeOfUpgrade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void defaultPlayerHasInitialStats() {
        Player p = new Player();

        assertEquals("Player", p.getName());
        assertEquals(30, p.getMaxHp());
        assertEquals(10, p.getBaseAttack());
        assertEquals(1.5, p.getBaseDefense());
        assertEquals(1, p.getLevel());
        assertEquals(0, p.getPoints());
    }

    @Test
    void namedConstructorSetsName() {
        Player p = new Player("Andrea");
        assertEquals("Andrea", p.getName());
    }

    @Test
    void resetRestoresMaxStats() {
        Player p = new Player();
        p.takeDamage(10);
        p.decreaseAtk(5.0);
        p.decreaseDef(1.0);

        p.reset();

        assertEquals(p.getMaxHp(), p.getHp());
        assertEquals(p.getMaxBaseAttack(), p.getBaseAttack());
        assertEquals(p.getMaxBaseDefense(), p.getBaseDefense());
    }

    @Test
    void copyConstructorPreservesStats() {
        Player original = new Player("A");
        original.setLevel(5);
        original.setPoints(3);
        original.setXp(100);

        Player copy = new Player(original);

        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getLevel(), copy.getLevel());
        assertEquals(original.getPoints(), copy.getPoints());
        assertEquals(original.getXp(), copy.getXp());
    }

    @Test
    void gainXpBelowThresholdDoesNotLevelUp() {
        Player p = new Player();
        int level = p.getLevel();
        p.gainXp(1.0);

        assertEquals(level, p.getLevel());
        assertEquals(1.0, p.getXp(), 0.0001);
    }

    @Test
    void gainXpAboveThresholdLevelsUp() {
        Player p = new Player();
        int initialLevel = p.getLevel();
        int initialPoints = p.getPoints();
        double initialMaxHp = p.getMaxHp();

        p.gainXp(100_000);

        assertTrue(p.getLevel() > initialLevel);
        assertTrue(p.getPoints() > initialPoints);
        assertTrue(p.getMaxHp() > initialMaxHp);
    }

    @Test
    void levelUpIncreasesStatsProportionally() {
        Player p = new Player();
        double baseHp = p.getMaxHp();
        double baseAtk = p.getMaxBaseAttack();
        double baseDef = p.getMaxBaseDefense();

        p.gainXp(10_000_000);

        assertTrue(p.getMaxHp() > baseHp);
        assertTrue(p.getMaxBaseAttack() > baseAtk);
        assertTrue(p.getMaxBaseDefense() > baseDef);
    }

    @Test
    void upgradeStatWithoutPointsDoesNothing() {
        Player p = new Player();
        double hp = p.getMaxHp();

        p.upgradeStat(TypeOfUpgrade.HP);

        assertEquals(hp, p.getMaxHp());
    }

    @Test
    void upgradeStatConsumesPoint() {
        Player p = new Player();
        p.setPoints(1);
        double maxHp = p.getMaxHp();

        p.upgradeStat(TypeOfUpgrade.HP);

        assertEquals(0, p.getPoints());
        assertTrue(p.getMaxHp() > maxHp);
    }

    @Test
    void displayColorUsesHpColor() {
        Player p = new Player();
        assertEquals(p.getColor(), p.getDisplayColor());
    }

    @Test
    void initializeScriptsAfterLoadCreatesBaseWhenEmpty() {
        Player p = new Player();
        p.setScripts(new java.util.ArrayList<>());

        p.initializeScriptsAfterLoad();

        assertNotNull(p.getScripts());
        assertFalse(p.getScripts().isEmpty());
    }
}