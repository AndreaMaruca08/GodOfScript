import game.logic.entity.enemies.ai.AIConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AIConfigTest {

    @Test
    void allConfigsHavePositiveDelays() {
        for (AIConfig c : AIConfig.values()) {
            assertTrue(c.delayMs > 0, c.name() + " delayMs");
            assertTrue(c.initialDelayMs > 0, c.name() + " initialDelayMs");
        }
    }

    @Test
    void allConfigsHavePositiveMultipliers() {
        for (AIConfig c : AIConfig.values()) {
            assertTrue(c.xpMultiplier > 0, c.name() + " xpMultiplier");
            assertTrue(c.statsMultiplier > 0, c.name() + " statsMultiplier");
        }
    }

    @Test
    void normalConfigHasExpectedValues() {
        assertEquals(1000, AIConfig.NORMAL.delayMs);
        assertEquals(2000, AIConfig.NORMAL.initialDelayMs);
        assertEquals(1.0, AIConfig.NORMAL.xpMultiplier, 0.0001);
        assertEquals(1.2, AIConfig.NORMAL.statsMultiplier, 0.0001);
    }

    @Test
    void fasterConfigsHaveLowerDelay() {
        assertTrue(AIConfig.VERY_FAST.delayMs < AIConfig.SLOW.delayMs);
        assertTrue(AIConfig.IMPOSSIBLE.delayMs < AIConfig.NORMAL.delayMs);
    }

    @Test
    void harderConfigsHaveHigherStatsMultiplier() {
        assertTrue(AIConfig.IMPOSSIBLE.statsMultiplier > AIConfig.NORMAL.statsMultiplier);
        assertTrue(AIConfig.IF_YOU_CAN.statsMultiplier > AIConfig.SUPER.statsMultiplier);
    }

    @Test
    void enumContainsAllExpectedEntries() {
        assertEquals(14, AIConfig.values().length);
    }
}