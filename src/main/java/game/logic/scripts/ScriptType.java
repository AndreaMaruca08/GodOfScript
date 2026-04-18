package game.logic.scripts;

import game.logic.scripts.all.level_based.lvl10.Dash;
import game.logic.scripts.all.level_based.lvl10.Knockback;
import game.logic.scripts.all.level_based.lvl15.Beam;
import game.logic.scripts.all.level_based.lvl15.Explosion;
import game.logic.scripts.all.level_based.lvl20.AttackBoost;
import game.logic.scripts.all.level_based.lvl20.HyperBeam;
import game.logic.scripts.all.level_based.lvl20.Regen;
import game.logic.scripts.all.level_based.lvl30.DefenseBoost;
import game.logic.scripts.all.level_based.lvl30.Rage;
import game.logic.scripts.all.standard.damage.BaseAttack;
import game.logic.scripts.all.standard.graphic_Info.Help;
import game.logic.scripts.all.standard.graphic_Info.ShowName;
import game.logic.scripts.all.standard.movements.Down;
import game.logic.scripts.all.standard.movements.Jump;
import game.logic.scripts.all.standard.movements.Left;
import game.logic.scripts.all.standard.movements.Right;
import game.logic.scripts.all.standard.movements.Up;
public enum ScriptType {
    UP, DOWN, LEFT, RIGHT, JUMP, BASE_ATTACK, HELP, SHOW_NAME, DASH,
    KNOCKBACK, BEAM, EXPLOSION, HYPER_BEAM, REGEN, ATK_BOOST, DEF_BOOST,
    RAGE;
    
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
            case REGEN -> new Regen();
            case ATK_BOOST -> new AttackBoost();
            case DEF_BOOST -> new DefenseBoost();
            case RAGE -> new Rage();
        };
    }
}