package game.logic.scripts.all.level_based.lvl30;

import game.logic.scripts.Script;

import java.util.List;

public final class Lvl30Scripts {
    private Lvl30Scripts() {}

    public static final List<Script> LVL_30_SCRIPTS = List.of(
        new DefenseBoost(), new Rage()
    );
}
