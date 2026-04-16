package game.logic.entity.enemies.ai;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AIConfig {
    VERY_SLOW      (2000,3000, 0.5, 1),
    SLOW           (1700,2000, 0.6, 1),
    SLOW_QUICK     (1600,500,  0.7, 1),
    NORMAL         (1000,2000, 1.0,   1.2),
    NORMAL_FAST    (900, 2000, 1.1, 1.4),
    FAST           (700, 1700, 1.3, 1.6),
    FAST_QUICK     (600, 400,  1.2, 1.7),
    VERY_FAST      (500, 1000, 1.5, 1.9),
    VERY_FAST_QUICK(400, 300,  1.6,   2.0),
    SUPER          (200, 1000, 1.8, 2.1),
    SUPER_QUICK    (200, 100,  2.0, 2.2),
    IMPOSSIBLE     (150, 50,   5,   5),
    DEATH          (25,  1,    20,  10),
    IF_YOU_CAN     (10,  1,    50,  20);

    public final int delayMs;
    public final int initialDelayMs;
    public final double xpMultiplier;
    public final double statsMultiplier;
}
