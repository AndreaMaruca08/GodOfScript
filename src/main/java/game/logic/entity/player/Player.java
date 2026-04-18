package game.logic.entity.player;

import game.logic.entity.Entity;
import game.logic.scripts.ScriptType;
import game.logic.scripts.all.level_based.lvl10.Lvl10Scripts;
import game.logic.scripts.all.level_based.lvl15.Lvl15Scripts;
import game.logic.scripts.all.level_based.lvl20.Lvl20Scripts;
import game.logic.scripts.all.level_based.lvl30.Lvl30Scripts;
import game.logic.scripts.all.standard.CommonScripts;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player extends Entity {

    public Player() {
        super(30, 10, 1.5, "Player");
    }

    public Player(Player playerToSave) {
        super(
                playerToSave.maxHp,
                playerToSave.maxBaseAttack,
                playerToSave.maxBaseDefense,
                playerToSave.name,
                playerToSave.scripts,
                playerToSave.xp,
                playerToSave.level,
                playerToSave.points
        );
    }

    public Player(String name) {
        super(30, 10, 1.5, name);
    }

    @Override
    public Color getDisplayColor() {
        return this.getColor();
    }

    public void reset(){
        this.hp = maxHp;
        this.baseAttack = maxBaseAttack;
        this.baseDefense = maxBaseDefense;
    }

    public void gainXp(double xp){
        this.xp += xp;
        if(this.xp >= nextLevelXp){
            this.xp -= nextLevelXp;
            nextLevelXp = nextLevelXp * 1.1;
            levelUp();
            gainXp(0.0);
        }
        DataSaver.savePlayer(this);
    }

    private void levelUp(){
        level++;
        maxHp = maxHp*1.08;
        maxBaseAttack = maxBaseAttack*1.08;
        maxBaseDefense = maxBaseDefense*1.08;

        hp = maxHp;
        baseAttack = maxBaseAttack;
        baseDefense = maxBaseDefense;

        points++;
        discoverNewScripts();
    }

    private void discoverNewScripts(){
        if(level >= 10 && hasNotScript(ScriptType.DASH)) {
            addScript(Lvl10Scripts.LVL10_SCRIPTS);
        }
        if(level >= 15 && hasNotScript(ScriptType.BEAM)) {
            addScript(Lvl15Scripts.LVL15_SCRIPTS);
        }
        if(level >= 20 && hasNotScript(ScriptType.HYPER_BEAM)) {
            addScript(Lvl20Scripts.LVL20_SCRIPTS);
        }
        if(level >= 30 && hasNotScript(ScriptType.DEF_BOOST)) {
            addScript(Lvl30Scripts.LVL_30_SCRIPTS);
        }
    }

    private boolean hasNotScript(ScriptType type) {
        return getScripts().stream()
                .map(this::getScriptType)
                .noneMatch(t -> t == type);
    }

    public void upgradeStat(TypeOfUpgrade stat){
        if(points <= 0)
            return;
        stat.upgrade(this);
        points--;
        DataSaver.savePlayer(this);
    }

    public void initializeScriptsAfterLoad() {
        if (scriptTypes != null && !scriptTypes.isEmpty()) {
            if (scripts == null || scripts.size() != scriptTypes.size()) {
                scripts = scriptTypes.stream()
                        .map(ScriptType::createScript)
                        .collect(Collectors.toCollection(ArrayList::new));
            }
        }
        else if (scripts == null || scripts.isEmpty()) {
            setScripts(new ArrayList<>(CommonScripts.BASE_SCRIPTS));
        }
    }
}