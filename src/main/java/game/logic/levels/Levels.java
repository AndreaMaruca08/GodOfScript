package game.logic.levels;

import java.util.List;

public final class Levels {
    private Levels() {}

    public static final List<Level> LEVELS = List.of(
            new Level1(), new Level2()
    );
}
