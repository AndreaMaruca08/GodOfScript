package game.logic.levels;

import game.logic.entity.enemies.ai.AIConfig;

import java.util.List;

public final class Levels {
    private Levels() {}

    public static final List<Level> LEVELS = List.of(
            new Level1(AIConfig.VERY_SLOW), new Level2(AIConfig.VERY_SLOW)
    );
}
