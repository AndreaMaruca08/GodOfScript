package game.logic.scripts.all.level_based.lvl20;

import game.logic.scripts.Script;

import java.util.List;

public final class Lvl20Scripts {
    private Lvl20Scripts() {}

    public static final List<Script> LVL20_SCRIPTS = List.of(
        new HyperBeam(), new Regen(), new AttackBoost()
    );
}
