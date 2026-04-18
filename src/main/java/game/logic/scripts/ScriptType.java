package game.logic.scripts;

import game.logic.scripts.level_based.lvl10.Dash;
import game.logic.scripts.level_based.lvl10.Knockback;
import game.logic.scripts.level_based.lvl15.Beam;
import game.logic.scripts.level_based.lvl15.Explosion;
import game.logic.scripts.level_based.lvl20.HyperBeam;
import game.logic.scripts.standard.damage.BaseAttack;
import game.logic.scripts.standard.graphic_Info.Help;
import game.logic.scripts.standard.graphic_Info.ShowName;
import game.logic.scripts.standard.movements.*;


public enum ScriptType {
    UP, DOWN, LEFT, RIGHT, JUMP, BASE_ATTACK, HELP, SHOW_NAME, DASH,
    KNOCKBACK, BEAM, EXPLOSION, HYPER_BEAM;
    
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
            case DASH -> new Dash();
            case KNOCKBACK -> new Knockback();
            case BEAM -> new Beam();
            case EXPLOSION -> new Explosion();
            case HYPER_BEAM -> new HyperBeam();
        };
    }
}