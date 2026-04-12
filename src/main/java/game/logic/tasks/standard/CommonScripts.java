package game.logic.tasks.standard;

import game.logic.tasks.Script;
import game.logic.tasks.standard.damage.BaseAttack;
import game.logic.tasks.standard.graphic_Info.Help;
import game.logic.tasks.standard.graphic_Info.ShowName;
import game.logic.tasks.standard.movements.*;

import java.util.List;

public final class CommonScripts {
    private CommonScripts() {}

    public static List<Script> BASE_SCRIPTS = List.of(
            new Up(), new Down(), new Left(), new Right(), new Jump(),
            new BaseAttack(),
            new Help(), new ShowName()
    );
}
