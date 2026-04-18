package game.logic.scripts.level_based.lvl15;

import game.logic.scripts.Script;

import java.util.List;

public final class Lvl15Scripts {
    private Lvl15Scripts() {}

    public static final List<Script> LVL15_SCRIPTS = List.of(
            new Beam(), new Explosion()
    );
}
