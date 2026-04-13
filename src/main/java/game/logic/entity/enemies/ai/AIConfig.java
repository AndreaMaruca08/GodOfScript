package game.logic.entity.enemies.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AIConfig {
    VERY_SLOW(3000,3000, 0.7),
    SLOW(2000,2000, 0.8),
    SLOW_QUICK(2000,500, 0.9),
    MEDIUM(1000,2000, 1),
    MEDIUM_FAST(900,2000, 1.1),
    FAST(700,1700, 1.4),
    FAST_QUICK(700, 400, 1.5),
    VERY_FAST(500, 1000, 1.7),
    VERY_FAST_QUICK(500, 300, 1.8),
    SUPER(300, 1000, 2),
    SUPER_QUICK(300, 100, 2.5),
    IMPOSSIBLE(150, 1, 4);

    private final int delayMs;
    private final int initialDelayMs;
    private final double xpMultiplier;
}
