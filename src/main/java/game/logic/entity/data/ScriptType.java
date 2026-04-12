package game.logic.entity.data;

import game.logic.tasks.Script;
import game.logic.tasks.standard.damage.BaseAttack;
import game.logic.tasks.standard.graphic_Info.Help;
import game.logic.tasks.standard.graphic_Info.ShowName;
import game.logic.tasks.standard.movements.*;


public enum ScriptType {
    UP, DOWN, LEFT, RIGHT, JUMP, BASE_ATTACK, HELP, SHOW_NAME;
    
    public Script createScript() {
        return switch (this) {
            case UP -> new Up();
            case DOWN -> new Down();
            case LEFT -> new Left();
            case RIGHT -> new Right();
            case JUMP -> new Jump();
            case BASE_ATTACK -> new BaseAttack();
            case HELP -> new Help();
            case SHOW_NAME -> new ShowName();
        };
    }
}