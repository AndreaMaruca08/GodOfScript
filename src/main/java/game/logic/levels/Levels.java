package game.logic.levels;

import java.util.List;

public final class Levels {
    private Levels() {}

    public static final List<Level> LEVELS = List.of(
            new Level1(), new Level2(), new Level3(), new Level4(), new Level5(), new Level6(),
            new Level7(), new Level8(), new Level9(), new Level10()
    );
}
