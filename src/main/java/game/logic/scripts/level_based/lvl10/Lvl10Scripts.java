package game.logic.scripts.level_based.lvl10;

import game.logic.scripts.Script;

import java.util.List;

public final class Lvl10Scripts {
    private Lvl10Scripts() {}

    public static final List<Script> LVL10_SCRIPTS = List.of(
            new Dash(), new Knockback()
    );
}
