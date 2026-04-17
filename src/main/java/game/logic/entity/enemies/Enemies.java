package game.logic.entity.enemies;

import java.util.List;

public final class Enemies {
    private Enemies() {}

    public static final List<Enemy> ENEMIES = List.of(
            new JuniorDev(null), new JavaScriptDev(null),
            new PythonDev(null), new CDev(null),
            new GoDev(null), new RustDev(null),
            new PhpDev(null), new JavaDev(null),
            new JavaDaddy(null)
    );
}
