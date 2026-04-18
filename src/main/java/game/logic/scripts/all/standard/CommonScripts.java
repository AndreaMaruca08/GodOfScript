package game.logic.scripts.all.standard;

import game.logic.scripts.Script;
import game.logic.scripts.all.standard.damage.BaseAttack;
import game.logic.scripts.all.standard.graphic_Info.Help;
import game.logic.scripts.all.standard.graphic_Info.ShowName;
import game.logic.scripts.all.standard.movements.*;

import java.util.List;

public final class CommonScripts {
    private CommonScripts() {}

    public static List<Script> BASE_SCRIPTS = List.of(
            new Up(), new Down(), new Left(), new Right(), new Jump(), //movement
            new BaseAttack(), //attack
            new Help(), new ShowName() //settings
    );
}
